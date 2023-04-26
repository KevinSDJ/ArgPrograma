package com.demo.app.dao;

import com.demo.app.domain.Nationality;
import com.demo.app.repository.AbstractCrudRepository;

public class NationalityDao extends AbstractCrudRepository<Nationality,Long> {
    private static NationalityDao instance;

    public static NationalityDao get(){
        instance= instance==null?new NationalityDao():instance;
        return instance;
    }
}
