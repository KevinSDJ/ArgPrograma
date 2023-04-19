package com.demo.app.adapters.outport;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface IRepository<T,S> {
    
    T findById(S id);
    T save(T entity) throws SQLException,IllegalAccessException,
    InvocationTargetException, NoSuchMethodException, SecurityException,NoSuchFieldException ;
    List<T> findAll();
    void deleteById(S id);
    T findByName(String name);
}
