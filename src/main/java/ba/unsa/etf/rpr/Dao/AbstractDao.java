package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Exceptions.StatementException;
import ba.unsa.etf.rpr.IDable;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public abstract class AbstractDao<T extends IDable> implements Dao<T>{
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
    public abstract T row2object(ResultSet rs) throws SQLException;
    public abstract Map<String, Object> object2row(T item);

    @Override
    public T getById(int id) throws StatementException {
        try {
            return executeQueryUnique("SELECT * FROM " + this.tableName + " WHERE ID = ?", new Object[]{id});
        } catch (StatementException e) {
            throw new StatementException("Error while loading by ID from database");
        }
    }
    @Override
    public T add(T item) throws StatementException {
        Map<String, Object> row = object2row(item);
        Map.Entry<String, String> columns = prepareInsert(row);

        String helpqry ;
        switch (this.tableName) {
            case "Departures" -> helpqry = "SELECT (MAX(d.ID) + 1) FROM Departures d;";
            case "Passengers" -> helpqry = "SELECT (MAX(d.ID) + 1) FROM Passengers d;";
            case "Tickets" -> helpqry = "SELECT (MAX(d.ID) + 1) FROM Tickets d;";
            case "Train_stations" -> helpqry= "SELECT (MAX(d.ID) + 1) FROM Train_stations d;";
            default -> helpqry = null;
        }
        int newID;
        try {
            PreparedStatement statement = getConnection().prepareStatement(helpqry);
            ResultSet rs = statement.executeQuery();
            rs.next();
            newID = rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(tableName);
        query.append(" (").append(columns.getKey()).append(") ");
        query.append("VALUES (").append(columns.getValue()).append(")");

        try {
            PreparedStatement statement = getConnection().prepareStatement(query.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()){
                if (entry.getKey().equals("ID"))continue;
                statement.setObject(counter, entry.getValue());
                ++counter;
            }
            statement.executeUpdate();
            item.setID(newID);
            return item;
        } catch (SQLException e) {
            throw new StatementException("Error while adding to database!");
        }

    }
    @Override
    public T update(T item) throws StatementException {
        Map<String, Object> row = object2row(item);
        String columns = prepareUpdate(row);
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ").append(this.tableName).append(" SET ").append(columns).append(" WHERE ID = ?");

        try {
            PreparedStatement statement = getConnection().prepareStatement(query.toString());
            int counter = 1;
            for (Map.Entry<String, Object> entry : row.entrySet()){
                if (entry.getKey().equals("ID"))continue;
                statement.setObject(counter, entry.getValue());
                ++counter;
            }
            statement.setObject(counter, item.getID());
            statement.executeUpdate();
            return item;

        } catch (SQLException e) {
            throw new StatementException("Error while updating the database!");
        }

    }
    @Override
    public void delete(int id) throws StatementException {
        String query = "DELETE FROM " + this.tableName + " WHERE ID = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setObject(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new StatementException("Error while deleting from database");
        }


    }
    @Override
    public List<T> getAll() throws StatementException {
        return executeQuery("SELECT * FROM " + this.tableName, null);
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

    private Map.Entry<String, String> prepareInsert(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        StringBuilder questions = new StringBuilder();

        int counter = 0;
        for (Map.Entry<String, Object> entry: row.entrySet()) {
            counter++;
            if (entry.getKey().equals("ID")){ //This serves as personal auto-increment as the one on the DB app isn't working for me
                switch (this.tableName) {
                    case "Departures" -> questions.append("(SELECT (MAX(d.ID) + 1) FROM Departures d)");
                    case "Passengers" -> questions.append("(SELECT (MAX(d.ID) + 1) FROM Passengers d)");
                    case "Tickets" -> questions.append("(SELECT (MAX(d.ID) + 1) FROM Tickets d)");
                    case "Train_stations" -> questions.append("(SELECT (MAX(d.ID) + 1) FROM Train_stations d)");
                }
                questions.append(",");
                columns.append(entry.getKey());
                columns.append(",");
                continue;
            }
            columns.append(entry.getKey());
            questions.append("?");
            if (row.size() != counter) {
                columns.append(",");
                questions.append(",");
            }
        }
        return new AbstractMap.SimpleEntry<>(columns.toString(), questions.toString());
    }

    private String prepareUpdate(Map<String, Object> row){
        StringBuilder columns = new StringBuilder();
        int counter = 0;
        for (Map.Entry<String, Object> entry : row.entrySet()){
            ++counter;
            if (entry.getKey().equals("ID"))continue;
            columns.append(entry.getKey()).append("= ?");
            if (row.size() != counter)columns.append(",");
        }
        return columns.toString();
    }
}
