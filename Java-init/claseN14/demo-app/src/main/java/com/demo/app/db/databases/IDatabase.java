package com.demo.app.db.databases;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabase {
    
    Connection connect() throws SQLException ;
}
