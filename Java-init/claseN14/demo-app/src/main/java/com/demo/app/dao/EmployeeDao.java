package com.demo.app.dao;

import com.demo.app.domain.Employee;
import com.demo.app.repository.AbstractCrudRepository;

public class EmployeeDao extends AbstractCrudRepository<Employee,Long> {

    private static EmployeeDao instance;

    public static EmployeeDao get(){
        instance= instance==null?new EmployeeDao():instance;
        return instance;
    }
}
