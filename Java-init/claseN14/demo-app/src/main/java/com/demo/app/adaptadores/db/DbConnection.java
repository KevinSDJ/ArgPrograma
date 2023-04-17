package com.demo.app.adaptadores.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection  implements IDbConnection {
    private String dbName;
    private int port;
    private String host;
    private String password;
    private String databaseType;
    private String user;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
            "jdbc:%s://%s:%s/%s".formatted(databaseType,host,port,dbName),user,password);
    }

    public void setDb(String dbName) {
        this.dbName=dbName;
    }

    public void setPort(int port) {
        this.port=port;
    }

    public void setHost(String host) {
        this.host=host;
    }

    public void setPassword(String password) {
        this.password=password;
    }
    public void setUser(String user){
        this.user=user;
    }
    public void setDatabaseType(String databaseType){
        this.databaseType=databaseType;
    }
}
