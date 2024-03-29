package com.demo.app.repository;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import com.demo.app.annotations.OneToOne;
import com.demo.app.annotations.Table;
import com.demo.app.db.ConnectionFactory;
import com.demo.app.db.databases.IDatabase;

public abstract class AbstractCrudRepository<T,S>  extends RepositoryUtils implements IRepository<T,S>{

    /** 
     * Aclaracion 
     * 
     * se que no era necesario que haga todo este rollo solo para practicar jdbc 
     * per quise practicar mas alla de solo jdbc, quise intentar hacer algo similar ( mi version berreta )
     * a Jpa , aunque no se si salio XD
    */

    private IDatabase database= ConnectionFactory.build().getCurrent();

    @Override
    public T save(T entity) throws SQLException{
        String tableName= getTableName(entity.getClass());
        Object tableId= getIdValue(entity);
        Object[] values= getValues(entity,null);
        String[] columns= getColumns(entity);
     
        // me falta verificar las relaciones oneToMany y ManyToMany

        if(tableId!=null){
            Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING,
            "Considere utilizar update si quiere actualizar un elemento existente");
            return null;
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ")
        .append(tableName)
        .append(" (").append(String.join(",", columns)).append(") ")
        .append(" values (")
        .append(String.join(",", Collections.nCopies(values.length, "?")))
        .append(")");
        try(Connection conn= database.connect();
        PreparedStatement stm=conn.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS)){
            conn.setAutoCommit(false);
            for (int i = 0; i < values.length; i++) {
                stm.setObject((i + 1), values[i]);
            }
            stm.executeUpdate();
            ResultSet rs= stm.getGeneratedKeys();
            if(rs.next()){
                Field idField= getIdField(entity);
                Class<?> type =idField.getType();
                try {
                    Long id= ((BigInteger)rs.getObject(1)).longValue();
                    entity.getClass().getMethod(getSetMethod(idField),type)
                    .invoke(entity,id);
                    conn.commit();
                } catch (SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    conn.rollback();
                    e.printStackTrace();
                }
            }          
        }catch(SQLException error){
            throw new SQLException(error);
        }
        return entity;
    }

    @Override
    public List<T> findAll() {
        List<T> list= new ArrayList<>();



        return list;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findById(S id,Class<?> entity) {
        Object entityFound=null;
        String sql= "select * from "+getTableName(entity)+" where id =?";
        try(Connection conn= database.connect();PreparedStatement stm= conn.prepareStatement(sql)){
            stm.setObject(1, (Object)id);
            ResultSet rs= stm.executeQuery();
            entityFound= entity.getConstructor().newInstance();
            if(rs.next()){
                for(Field field:entityFound.getClass().getDeclaredFields()){
                    Class<?> type = field.getType();
                    String column= field.getName();
                    if(field.isAnnotationPresent(OneToOne.class)){
                        column=column+"_id";
                        entityFound.getClass().getMethod(getSetMethod(field),type)
                        .invoke(entityFound,findById((S) rs.getObject(column),type));
                    }else{
                        entityFound.getClass().getMethod(getSetMethod(field),type)
                        .invoke(entityFound, rs.getObject(column));
                    }
                }
            }
        
        }catch(SQLException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex){
            Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, ex.getMessage(),ex);
        }
        return (T) entityFound;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findOrCreate(T entity) {
        Object id=getIdValue(entity);
        if(id==null){
            try{
                entity= save(entity);
            }catch(SQLException ex ){
                Boolean isDuplicateError=ex.getCause()
                .getClass().equals(SQLIntegrityConstraintViolationException.class);
                Boolean containNameField= ex.getMessage().contains("name");
                if(isDuplicateError&&containNameField){
                    try {
                        String name = String.valueOf(entity.getClass().getMethod(getGetMethod(entity.getClass().getDeclaredField("name"))).invoke(entity)); 
                        return findByName(name, entity.getClass());
                    } catch (NoSuchFieldException | SecurityException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } 
                }else{
                    Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING,ex.getMessage(),ex);
                    return null;
                }
            }
        }else{
            return findById((S) id, entity.getClass());
        }
        return entity;
    }

    @Override
    public void delete(T entity) {

        String sql= "delete from "
        + getTableName(entity.getClass())+" where ";
        for(Field field:entity.getClass().getDeclaredFields()){
            if(getFieldValue(field, entity)!=null){
                sql+= field.getName()+"="+getFieldValue(field, entity);
                break;
            }
        }
        if(!sql.contains("null")||!sql.contains("Null")){
            try(Connection conn= database.connect();Statement stm= conn.createStatement()){
                stm.executeUpdate(sql);
            }catch(SQLException ex){
                Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, ex.getMessage(), ex);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T findByName(String name,Class<?> clazz) {
        Field[] properties= clazz.getDeclaredFields();
        if(Stream.of(properties).anyMatch(e-> e.getName().equals("name"))){

            String query = "SELECT * FROM "
            +clazz.getAnnotation(Table.class).name()
            + " WHERE name = ?";
            try (Connection conn=database.connect();PreparedStatement stm=conn.prepareStatement(query)) {
                stm.setString(1, name);
                Object object = clazz.getConstructor().newInstance();
                ResultSet rs= stm.executeQuery();
                if(rs.next()){
                    for(Field field:object.getClass().getDeclaredFields()){
                        Class<?> type= field.getType();
                        object.getClass()
                        .getMethod(getSetMethod(field),type)
                        .invoke(object, rs.getObject(field.getName()));
                    }
                    return (T) object;
                }
            } catch (Exception e) {
                Logger.getLogger(getClass().getSimpleName()).log(Level.WARNING, e.getMessage(),e);
            }
        }
        return null;
    }
}
