package com.demo.app.annotations;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class EntityScanAnnotation {

    private Object entity;
    private Boolean hasOneToOne=false;
    private EntityScanAnnotation oneToOne;

    public EntityScanAnnotation(Object object){
        entity= object;
        for(Field field:object.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(OneToOne.class)){
                hasOneToOne=true;
                String methodGet="get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
                try {
                    oneToOne= new EntityScanAnnotation(object.getClass().getMethod(methodGet).invoke(object));
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException
                        | SecurityException e) {
                    e.printStackTrace();
                }
            }
        }
        System.gc();

    }
    public Class<?> getIdType(){
        Class<?> type=null;
        for(Field field:entity.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                try {
                    type=field.getType();
                } catch (SecurityException e) {
                    e.printStackTrace();
                }

            }
        }
        return type;
    }
    public Object getIdValue(){
        Object value=null;
        for(Field field:entity.getClass().getDeclaredFields()){
            if(field.isAnnotationPresent(Id.class)){
                String methodGet="get"+field.getName().substring(0, 1).toUpperCase()+field.getName().substring(1);
                try {
                    value=entity.getClass().getMethod(methodGet).invoke(entity); 
                } catch (IllegalAccessException|InvocationTargetException|NoSuchMethodException|SecurityException e) {
                    e.printStackTrace();
                }

            }
        }
        return value;
    }

    public Boolean getHasOneToOne() {
        return hasOneToOne;
    }
    public EntityScanAnnotation getOneToOneDepend(){
        return oneToOne;
    }

}
