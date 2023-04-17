package com.demo.app.adaptadores.db;

public class MySqlDb extends DbConnection {
    private String user= "root";// default
    private int port=3306;  //default
    private static MySqlDb instance=new MySqlDb();

    private MySqlDb(){
        setUser(user);
        setPort(port);
        setDatabaseType("mysql");
    }

    public static MySqlDb build(){
        return instance;
    } 
}
