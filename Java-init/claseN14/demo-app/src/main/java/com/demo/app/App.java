package com.demo.app;

import java.sql.SQLException;

import com.demo.app.adaptadores.db.MySqlDb;

public class App 
{
    public static void main( String[] args )
    {

       MySqlDb db = MySqlDb.build();
       
    }
}
