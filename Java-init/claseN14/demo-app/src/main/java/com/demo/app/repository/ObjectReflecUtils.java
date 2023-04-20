package com.demo.app.repository;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public abstract class ObjectReflecUtils<T> {
    /**
     * estuve probando la api reflect de java para poder 
     * abstraer los metodos crud sin repetir codigo
     * esta api es increiblemente util y maravilloso , aunque tiene un coste tecnico
     * ,usa los datos de las objetos/clases guardadas por java en la jvm para trabajar con los objetos sin saber
     * exactamente a que clase pertenecen , eso si , dicha clase debe existir o sino no
     * funciona obviamente.
     */ 

    
    Field[] getFieldsDeclared(Object object){

        return  object.getClass().getDeclaredFields();
    }

    String[] getFieldsToArrayString(Object object){

        return Stream.of(getFieldsDeclared(object)).map(e->e.getName()).toArray(String[]::new);
    }

    Object[] getValues(T object){
        Object[] values= Stream.of(getFieldsDeclared(object)).map(e->{
            String method="get"+e.getName().substring(0,1).toUpperCase()+e.getName().substring(1);
            try {
                return object.getClass().getMethod(method).invoke(object);
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (NoSuchMethodException e1) {
                e1.printStackTrace();
            } catch (SecurityException e1) {
                e1.printStackTrace();
            }
            return null;
        }).toArray();
        
        return values;
    }
}
