package com.demo.app.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import com.demo.app.adapters.db.DbConnection;
import com.demo.app.adapters.outport.IRepository;

public class EntityRepository<T, S> extends ObjectReflecUtils<T> implements IRepository<T, S> {

    private DbConnection db = null;

    public void setDbConnection(DbConnection dbConnection) {
        this.db = dbConnection;
    }

    @Override
    public T findById(S id) {
        return null;
    }

    @Override
    public T save(T entity) throws SQLException, IllegalAccessException, NoSuchMethodException,
            InvocationTargetException, SecurityException, NoSuchFieldException {
        if (db == null)
            return null;
        String[] fields = getFieldsDeclared(entity);

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ")
                .append(entity.getClass().getSimpleName().toLowerCase())
                .append(" (").append(String.join(",", fields)).append(")")
                .append("values (")
                .append(String.join(",", Collections.nCopies(fields.length, "?"))).append(")");

        try (Connection conn = db.getConnection();
                PreparedStatement stm = conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)) {
            Object[] values = getValues(entity);
            conn.setAutoCommit(false);
            for (int i = 0; i < values.length; i++) {
                stm.setObject((i + 1), values[i]);
            }
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Fail to save " + entity.getClass().getSimpleName());
            }
            try (ResultSet st = stm.getGeneratedKeys()) {
                while (st.next()) {
                    Class<?> type= entity.getClass().getDeclaredField(fields[0]).getType();
                    String setMethod = "set" + fields[0].substring(0, 1).toUpperCase() + fields[0].substring(1);
                    entity.getClass().getMethod(setMethod,type).invoke(entity,type.cast(st.getLong(1)));
                    conn.commit();
                }
            } catch (NoSuchMethodException ex) {
                conn.rollback();
                throw new NoSuchMethodException(ex.getMessage());
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(S id) {
        // TODO Auto-generated method stub

    }

    @Override
    public T findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

}
