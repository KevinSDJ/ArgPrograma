package com.demo.app.adaptadores.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDbConnection {
    
    Connection getConnection() throws SQLException;
    void setDb(String dbName);
    void setPort(int port);
    void setHost(String host);
    void setPassword(String password);
    void setUser(String user);
    void setDatabaseType(String databaseType);
}
