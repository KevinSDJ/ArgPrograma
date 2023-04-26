package com.demo.app.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;
import java.util.stream.Stream;
import com.demo.app.annotations.Id;
import com.demo.app.annotations.OneToOne;
import com.demo.app.annotations.Table;

public abstract class RepositoryUtils {
    
    protected String[] getColumns(Object object){

        String[] columns= Stream.of(object.getClass().getDeclaredFields())
        .map(e->{
            String column=e.getName();
            if(e.isAnnotationPresent(OneToOne.class)){
                column = e.getName()+"_id";
            }
            return column;
        })
        .toArray(String[]::new);

        return columns;
    }

    protected Object[] getValues(Object object,Function<Integer,Void> verifyIfExist){
        Object[] values= Stream.of(object.getClass().getDeclaredFields())
        .map(f->{
            Object value=null;
            
            if(f.isAnnotationPresent(OneToOne.class)){
                String method=getGetMethod(f);
                f.setAccessible(true);
                try {
                    Object v= object.getClass().getMethod(method).invoke(object);
                    return getIdValue(v);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    value= object.getClass().getMethod(getGetMethod(f)).invoke(object);
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e) {
                    e.printStackTrace();
                }
            }
            return value;
        })
        .toArray(Object[]::new);
        return values;
    }

    protected String getTableName(Object object){
        if(object.getClass().isAnnotationPresent(Table.class)){
            Table table= object.getClass().getAnnotation(Table.class);
            return table.name();
        }
        return null;
    } 
    protected Object getIdValue(Object object){
        Field idField= getIdField(object);
        Object id=null;
        try {
            id = object.getClass().getMethod(getGetMethod(idField)).invoke(object);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
            e.printStackTrace();
        }
        return id;
    }
    protected Field getIdField(Object object){
        Field idField=null;
        for(Field field:object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                try {
                    idField=field;
                    break;
                } catch (SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
        return idField;
    }

    protected String getSetMethod(Field field){

        String method="set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
        return method;
    }
    
    protected String getGetMethod(Field field){
        String method="get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1);
        return method;
    }

}
