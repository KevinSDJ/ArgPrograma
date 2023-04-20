package com.demo.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.demo.app.adapters.db.DbConnection;
import com.demo.app.annotations.EntityScanAnnotation;
import com.demo.app.db.MySqlDb;
import com.demo.app.entity.Department;
import com.demo.app.entity.Employee;
import com.demo.app.entity.Nationality;

public class App {
    private static DbConnection db;
    private static List<String> schemas = new ArrayList<>();
    private static Boolean hasSchemas = false;

    public static void main(String[] args) throws IOException {
        
        Employee example= new Employee(1233423,"Kevin","De jesus");
        example.setNationality(new Nationality("Argentina"));
        example.setDepartment(new Department("logistica",2238934L));
        //repository.testAnotation(example);

        /*Nationality d=null;
        try {
            d = repository.save(new Nationality("Argentina"));
            System.out.println("Objeto salvado "+ d.toString());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException
                | SQLException | NoSuchFieldException  e) {
            e.printStackTrace();
        }*/
      
    }

    private static void initializeDatasources() throws IOException {
        configDb();
        testConnection();
        preparedSchemas();
        loadSchemas();
    }

    private static void configDb() {
        db = MySqlDb.build();
        db.setDb("test");
        db.setPassword("12345");
    }

    private static void testConnection() {
        try (Connection conn = db.getConnection();) {
            System.out.println(conn.getMetaData().getDatabaseProductName());
            System.out.println("Connection success");
        } catch (SQLException ex) {
            System.out.println(
                    "\n /!\\ Connection failure url, please check your configuration, driver or instance of mysql to connect");
            System.exit(0);
        }

    }

    private static void preparedSchemas() throws IOException {

        if (hasSchemas) {
            return;
        }
        InputStream input = App.class.getClassLoader().getResourceAsStream("schema.sql");
        InputStreamReader reader = new InputStreamReader(input, "UTF-8");
        BufferedReader output = new BufferedReader(reader);

        String schema = "";
        String line = null;
        while ((line = output.readLine()) != null) {
            if (!line.isEmpty()) {
                line = line.trim();
                if (line.contains(";")) {
                    schema += line;
                    schemas.add(schema);
                    schema = "";
                } else {
                    schema += line;
                }
            }
        }
        output.close();
        reader.close();
        input.close();
        hasSchemas = true;
    }

    private static void loadSchemas() {
        try (Connection conn = db.getConnection();) {
            schemas.forEach(e -> {
                Statement stm;
                try {
                    stm = conn.createStatement();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    try {
                        conn.close();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                    return;
                }

                try {
                    int res = stm.executeUpdate(e);
                    System.out.println("table loaded: " + res);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static boolean existNationality(Nationality nationality) {
        String sqlQueryIfExist = """
                 SELECT CASE WHEN EXISTS (SELECT * FROM nationality WHERE name=lower(?))
                 THEN 'TRUE'
                 ELSE 'FALSE' END
                """;
        boolean exist=false;
        try (Connection conn = db.getConnection(); PreparedStatement pstm = conn.prepareStatement(sqlQueryIfExist)) {
            pstm.setString(1, nationality.getName());
            ResultSet resulQuery = pstm.executeQuery();
            exist = resulQuery.next() ? resulQuery.getBoolean(1) : false;
            resulQuery.close();
            pstm.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return exist;
    }
}
