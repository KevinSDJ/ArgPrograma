package com.demo.app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.demo.app.dao.NationalityDao;
import com.demo.app.db.ConnectionFactory;
import com.demo.app.domain.Nationality;
import com.demo.app.repository.InitializeDataSource;

public class App {
   
    public static void main(String[] args) throws IOException {
        
        /**
         * configuro la base de datos que voy a usar,
         * e inicializo las tablas y  si es necesario precargo los datos necesarios 
         * pasandole una funcion como parametro a run()
         */

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
        System.out.println(nationality);
    }

    public static void preloadData(){
        System.out.println("precargar departamentos o nacionalidades");
    }
}
