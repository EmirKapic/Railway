package ba.unsa.etf.rpr.Dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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

}
