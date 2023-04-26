package com.demo.app.db;

import com.demo.app.db.databases.IDatabase;

public interface IConnectionFactory  {

    interface IConnections{
        public static IConnections build(){return null;}; 
        IDatabase createMySqlDb() throws Exception;
        IDatabase getCurrent();
        IConnectionConfig config();
    }
    interface IConnectionConfig{
       abstract IConnectionConfig setPort(Long port);
       abstract IConnectionConfig setHost(String host);
       abstract IConnectionConfig setUser(String user);
       IConnectionConfig setPassword(String password);
       IConnectionConfig setDatabaseName(String databaseName);
       IConnections doneConfig();
    }
}
