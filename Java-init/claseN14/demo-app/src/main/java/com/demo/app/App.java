package com.demo.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.demo.app.adapters.db.DbConnection;
import com.demo.app.db.MySqlDb;
import com.demo.app.entity.Department;
import com.demo.app.entity.Employee;
import com.demo.app.entity.Nationality;
import com.demo.app.repository.EntityRepository;

public class App {
    private static final Department[] departaments = {
            new Department("logistica", 4213321312L),
            new Department("sistemas", null),
            new Department("compras", 8012323213L)
    };
    private static DbConnection db;
    private static List<String> schemas = new ArrayList<>();
    private static Boolean hasSchemas = false;

    public static void main(String[] args) throws IOException {
        
        initializeDatasources();

        EntityRepository<Nationality,Long> repository = new EntityRepository<>();
        repository.setDbConnection(db);
        
        Nationality d=null;
        try {
            d = repository.save(new Nationality("Argentina"));
            System.out.println("Objeto salvado "+ d.toString());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SecurityException
                | SQLException | NoSuchFieldException  e) {
            e.printStackTrace();
        }
      
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

    private static void insertDepartaments() {

        try (Connection conn = db.getConnection()) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(*) as count FROM departament");
            int count = 0;
            while (rs.next()) {
                count = rs.getInt("count");
            }
            rs.close();
            st.close();
            if (count < 1) {
                for (Department departament : departaments) {
                    String sql = "insert into departament (name,current_budget) values(?,?)";
                    try (PreparedStatement stm = conn.prepareStatement(sql);) {
                        stm.setString(1, departament.getName());
                        if (departament.getCurrent_budget() != null) {
                            stm.setLong(2, departament.getCurrent_budget());
                        } else {
                            stm.setNull(2, Types.BIGINT);
                        }

                        stm.executeUpdate();
                    } catch (SQLException ex) {
                        throw new SQLException(ex);
                    }
                }
            }
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

    private static Long saveNationality(Nationality nationality) {

        String sqlwrite = "INSERT INTO nationality (name) values(?)";
        if(existNationality(nationality)){
            return null;
        }
        Long id=null;
        try (Connection conn= db.getConnection();PreparedStatement stmupd = conn.prepareStatement(sqlwrite, Statement.RETURN_GENERATED_KEYS)) {
            stmupd.setString(1, nationality.getName());
            int affectedRows = stmupd.executeUpdate();
            if(affectedRows==0){
                throw new SQLException("Fail to save nationality");
            }
            try(ResultSet reultsetKeys= stmupd.getGeneratedKeys()){
                if (reultsetKeys.next()) {
                    id=reultsetKeys.getLong(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            stmupd.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return id;
    }

    private static Department findDepartamentByName(String name){
        Department department=null;
        String query= "select * from department where name=?";
        try(Connection conn= db.getConnection();PreparedStatement stm= conn.prepareStatement(query)){
            ResultSet result= stm.executeQuery();
            department= new Department();
            while(result.next()){
                department.setId(result.getLong("id"));
                department.setCurrent_budget(result.getLong("current_budget"));
            }
            result.close();
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return department;
    }

    private static void insertEmployees(){
        List<Employee> employees= new ArrayList<>();
        Employee nEmployee= new Employee(324232123,"Kevin","Gomez");
        nEmployee.setNationality(new Nationality("Argentina"));
        nEmployee.setDepartment(findDepartamentByName("logistica"));
        Employee nEmployee2= new Employee(324232123,"Juan","Niclich");
        nEmployee2.setNationality(new Nationality("Mexico"));
        nEmployee2.setDepartment(findDepartamentByName("sistemas"));
        employees.add(nEmployee);
        employees.add(nEmployee2);

          
        employees.forEach(System.out::println);
    }
}
