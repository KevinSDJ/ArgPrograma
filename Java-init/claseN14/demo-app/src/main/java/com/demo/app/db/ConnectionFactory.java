package com.demo.app.db;

import com.demo.app.db.IConnectionFactory.IConnectionConfig;
import com.demo.app.db.IConnectionFactory.IConnections;
import com.demo.app.db.databases.IDatabase;
import com.demo.app.db.databases.MySqlDb;

public class ConnectionFactory implements IConnectionFactory.IConnectionConfig
,IConnectionFactory.IConnections {
    private static IConnections instance;
    
    private long port=0;
    private String host="localhost";
    private String user="";
    private String password="";
    private String databaseName="";

    private IDatabase currentDatabase;
    
    private ConnectionFactory(){
    }

    public static IConnections build() {
        instance= instance==null? new ConnectionFactory():instance;
        return instance;
    }

    @Override
    public IDatabase createMySqlDb() throws Exception{
        if(databaseName.isEmpty()){
            throw new Exception("el nombre de la base de datos es necesario");
        }
        currentDatabase= new MySqlDb(user,password,databaseName).setHost(host).setPort(port);
        return currentDatabase;
    }

    @Override
    public IDatabase getCurrent(){
        return currentDatabase;
    }

    @Override
    public IConnectionConfig config() {
         return (IConnectionConfig) instance;
    }

    @Override
    public IConnectionConfig setPort(Long port) {
       this.port= port;
       return (IConnectionConfig) instance;
    }

    @Override
    public IConnectionConfig setHost(String host) {
       this.host=host;
       return (IConnectionConfig) instance;
    }

    @Override
    public IConnectionConfig setUser(String user) {
       this.user=user;
       return (IConnectionConfig) instance;
    }

    @Override
    public IConnectionConfig setPassword(String password) {
        this.password= password;
        return (IConnectionConfig) instance;
    }

    @Override
    public IConnectionConfig setDatabaseName(String databaseName) {
        this.databaseName= databaseName;
        return (IConnectionConfig) instance;
    }

    @Override
    public IConnections doneConfig() {
        return (IConnections) instance;
    }
    
}
