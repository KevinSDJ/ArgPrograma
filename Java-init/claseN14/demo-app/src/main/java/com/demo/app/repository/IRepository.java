package com.demo.app.repository;

import java.sql.SQLException;
import java.util.List;

public interface IRepository<T,S> {
    
    abstract T findById(S id,Class<?> entity);
    abstract T findOrCreate(T entity);
    T save(T entity) throws SQLException;
    List<T> findAll();
    void delete(T entity);
    abstract T findByName(String name,Class<?> clazz);
}
