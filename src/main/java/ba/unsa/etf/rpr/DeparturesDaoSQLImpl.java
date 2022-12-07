package ba.unsa.etf.rpr;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Mysql implementation of DeparturesDao
 */
public class DeparturesDaoSQLImpl implements DeparturesDao{
    private Connection connection;

    public DeparturesDaoSQLImpl(){

        try {
            FileReader reader = new FileReader("D:\\Java Projects\\Railway\\src\\main\\resources\\db.properties");
            Properties p = new Properties();
            p.load(reader);
            this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage() + " WARNING: failed to connect");
        }
    }

    /**
     * looks for a departure with the given ID
     * @param id primary key in the DB
     * @return The departure you looked for, or null if it doesn't exist
     */
    @Override
    public Departures getById(int id) {
        String query = "SELECT * FROM Departures d WHERE d.DeparturesID = ?"; //First query, gets everything except start/end time (which requires a different format of query)
        try {
            //Connects to the database
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (!result.next())return null;

            Departures dep = new Departures();
            dep.setDepartureID(result.getInt(1));
            dep.setStartDate(result.getObject(2, LocalDateTime.class));
            dep.setEndDate(result.getObject(3, LocalDateTime.class));
            dep.setLength(result.getString(4));
            dep.setTicketsLeft(result.getInt(5));
            dep.setStartStationID(result.getInt(6));
            dep.setEndStationID(result.getInt(7));
            dep.setTicketsTotal(result.getInt(8));

            //Now we need start and end time
            query = "SELECT DATE_FORMAT(d.Start_date, '%H:%i'), DATE_FORMAT(d.End_date, '%H:%i') FROM Departures d WHERE d.DeparturesID = ?";
            statement = this.connection.prepareStatement(query);
            statement.setInt(1,id);
            result = statement.executeQuery();

            if (!result.next())return null;
            dep.setStartTime(result.getString(1));
            dep.setEndTime(result.getString(2));

            return dep;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Departures add(Departures item) {
        String query = "insert into Departures(DeparturesID, Start_date, End_date, Length, Tickets_left, Start_station_ID, End_station_ID, Tickets_total) values ((SELECT (MAX(d.DeparturesID) + 1) FROM Departures d), ?, ?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setObject(1, item.getStartDate());
            statement.setObject(2, item.getEndDate());
            statement.setTime(3, Time.valueOf(item.getLength()));
            statement.setInt(4, item.getTicketsLeft());
            statement.setInt(5,item.getStartStationID());
            statement.setInt(6,item.getEndStationID());
            statement.setInt(7,item.getTicketsTotal());
            statement.executeUpdate();
            return item;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Departures update(Departures item) {
        String query = "UPDATE Departures d SET Start_date = ?, End_date = ?, Length = ?, Tickets_left = ?, Start_station_ID = ?, End_station_ID = ?, Tickets_total = ? WHERE d.DeparturesID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setObject(1, item.getStartDate());
            statement.setObject(2, item.getEndDate());
            statement.setTime(3, Time.valueOf(item.getLength()));
            statement.setInt(4, item.getTicketsLeft());
            statement.setInt(5,item.getStartStationID());
            statement.setInt(6,item.getEndStationID());
            statement.setInt(7,item.getTicketsTotal());
            statement.setInt(8,item.getDepartureID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Departures delete(Departures item) {
        String query = "DELETE FROM Departures WHERE DeparturesID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getDepartureID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Departures> getAll() {
        return null;
    }

    @Override
    public List<Departures> searchByStartDate(Date startDate) {
        return null;
    }

    @Override
    public List<Departures> searchByStation(TrainStations location) {
        return null;
    }
}
