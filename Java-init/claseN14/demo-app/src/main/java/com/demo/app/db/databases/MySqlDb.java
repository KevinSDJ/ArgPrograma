package com.demo.app.db.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDb implements IDatabase {
    private String host= "localhost";
    private long port= 3306;
    private String user="root";
    private String password="";
    private String dbName="";

    public MySqlDb(String user,String password,String dbName){
        this.user= user.isBlank()?this.user:user;
        this.password= password.isBlank()?this.password:password;
        this.dbName= dbName;
    }

    public MySqlDb setPort(long port){
        this.port= port==0?this.port:port;
        return this;
    }
    public MySqlDb setHost(String host){
        this.host=host;
        return this;
    }

    @Override
    public Connection connect() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://%s:%s/%s"
        .formatted(host,port,dbName), user, password);
    }

}
