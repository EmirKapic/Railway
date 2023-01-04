package ba.unsa.etf.rpr;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static DBConnection dbObject;
    private Connection connection;
    private DBConnection(){

    }
    public static DBConnection getInstance(){
        if (dbObject == null){
            dbObject = new DBConnection();
        }
        return dbObject;
    }

    public Connection getConnection(){
        try {
            if (connection == null){
                Properties p = new Properties();
                p.load(ClassLoader.getSystemResource("db.properties").openStream());
                connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
            }
            return connection;
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
