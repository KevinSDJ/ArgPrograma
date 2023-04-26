package com.demo.app.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.demo.app.db.databases.IDatabase;

public class InitializeDataSource {
    private IDatabase db;
    private List<String> schemas = new ArrayList<>();

    public InitializeDataSource(IDatabase db) throws Exception {
        this.db =db;
    }

    public void run() throws IOException {
        testConnection();
        preparedSchemas();
        loadSchemas();

    }
    public void run(Runnable afterLoadTables) throws IOException {
        testConnection();
        preparedSchemas();
        loadSchemas();
        afterLoadTables.run();
    }

    private void testConnection() {
        
        try (Connection conn=db.connect()){
            System.out.println(conn.getMetaData().getDatabaseProductName());
            System.out.println("Connection success");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("""
                /!\\ Conexion fallida, verifica que la instancia de Mysql este activa,
                    o las configuracion de conexion este correcta
                    """);
            System.exit(0);
        }
    }

    private void preparedSchemas() throws IOException {

        InputStream input = getClass().getClassLoader().getResourceAsStream("schema.sql");
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
    }

    private  void loadSchemas() {        
        try (Connection conn = db.connect()) {
            schemas.forEach(schema -> {
                try(Statement stm =conn.createStatement()) {
                    stm.executeUpdate(schema);
                } catch (SQLException error) {
                    error.printStackTrace();
                    try {
                        conn.close();
                    } catch (SQLException errorClose) {
                        errorClose.printStackTrace();
                    }
                    return;
                }
            });
        } catch (SQLException errorToLoad) {
            errorToLoad.printStackTrace();
        }
    }


}
