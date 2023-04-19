package com.demo.app.db;

import com.demo.app.adapters.db.DbConnection;

public class MySqlDb extends DbConnection {
    private String user= "root";// default
    private int port=3306;  //default
    private String host="localhost"; // default
    private static MySqlDb instance=new MySqlDb();

    private MySqlDb(){
        setUser(user);
        setPort(port);
        setDatabaseType("mysql");
        setHost(host);
    }

    public static MySqlDb build(){
        return instance;
    } 
}
