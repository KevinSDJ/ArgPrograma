package com.demo.app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.demo.app.dao.DepartmentDao;
import com.demo.app.dao.EmployeeDao;
import com.demo.app.dao.NationalityDao;
import com.demo.app.db.ConnectionFactory;
import com.demo.app.domain.Department;
import com.demo.app.domain.Employee;
import com.demo.app.domain.Nationality;
import com.demo.app.repository.InitializeDataSource;

public class App {
   
    public static void main(String[] args) throws IOException {

        try {
            new InitializeDataSource(ConnectionFactory.build().config()
            .setPassword("12345")
            .setDatabaseName("test")
            .doneConfig().createMySqlDb()).run(App::preloadData);
        } catch (Exception e) {
            Logger.getLogger(InitializeDataSource.class.getSimpleName()).log(Level.SEVERE, e.getMessage(), e);;
            System.exit(0);
        }

        Nationality nationality= NationalityDao.get().findOrCreate(new Nationality("Argentina"));
        Department department = DepartmentDao.get().findOrCreate(new Department("Logistica",null));
      
        Employee employee= new Employee(4355432L, "Kevin", "De jesus");
        employee.setDepartment(department);
        employee.setNationality(nationality);
    
        try {
            EmployeeDao.get().save(employee);
        } catch (SQLException e) {
            Logger.getLogger(e.getClass().getSimpleName())
            .log(Level.WARNING, e.getMessage(),e);
        }


    }

    public static void preloadData(){
        
    }
}
