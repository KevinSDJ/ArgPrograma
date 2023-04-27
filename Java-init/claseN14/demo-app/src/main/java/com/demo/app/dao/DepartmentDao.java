package com.demo.app.dao;

import com.demo.app.domain.Department;
import com.demo.app.repository.AbstractCrudRepository;

public class DepartmentDao extends AbstractCrudRepository<Department,Long>{
    private static DepartmentDao instance;

    public static DepartmentDao get(){
        instance= instance==null?new DepartmentDao():instance;
        return instance;
    }
}
