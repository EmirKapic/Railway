package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.StatementException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public abstract class AbstractDao<T> implements Dao<T>{
    private Connection connection;
    private String tableName;
    public AbstractDao(String tableName){
        this.tableName = tableName;
        Properties p = new Properties();
        try {
            p.load(ClassLoader.getSystemResource("db.properties").openStream());
            try {
                this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
            } catch (SQLException e) {
                System.out.println(e.getMessage() + "\nCould not open the connection to database!");
            }
        } catch (IOException e) {
            System.out.println("Could not open properties file!");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public abstract T row2object(ResultSet rs);
    public abstract Map<String, Object> object2row(T item);

    @Override
    public T getById(int id) {
        try {
            return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE ID = ?", new Object[]{id});
        } catch (StatementException e) {
            System.out.println(e.getMessage());
            System.exit(1);
            return null;
        }
    }
    @Override
    public T add(T item){
        return null;
    }
    @Override
    public T update(T item){
        return null;
    }
    @Override
    public void delete(int id){

    }
    @Override
    public List<T> getAll(){
        return null;
    }


    public List<T> executeQuery(String query, Object[] parameters) throws StatementException {
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            if (parameters != null)
                for (int i = 0; i < parameters.length; ++i)
                    statement.setObject(i+1, parameters[i]);

            ResultSet resultSet = statement.executeQuery();
            List<T> resList = new ArrayList<>();
            while (resultSet.next()){
                resList.add(row2object(resultSet));
            }
            return resList;
        } catch (SQLException e) {
            throw new StatementException(e.getMessage(), e);
        }
    }

    public T executeQueryUnique(String query, Object[] parameters) throws StatementException {
        List<T> result = executeQuery(query, parameters);
        if (result!=null && result.size() == 1)return result.get(0);
        else throw new StatementException("Result not correct!");
    }

    private Map.Entry<String, String> prepareInsertParts(Map<String, Object> row){
        return null;
    }

    private String prepareUpdateParts(Map<String, Object> row){
        return null;
    }
}
