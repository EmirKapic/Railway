package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Tickets;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TicketsDaoSQLImpl implements TicketsDao{
    private Connection connection;

    public TicketsDaoSQLImpl(){
        try {
            FileReader reader = new FileReader("D:\\Java Projects\\Railway\\src\\main\\resources\\db.properties");
            Properties p = new Properties();
            p.load(reader);
            this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage() + " WARNING: failed to connect");
        }
    }
    @Override
    public Tickets getById(int id) {
        String query = "SELECT * FROM Tickets d WHERE d.TicketID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (!result.next())return null;

            Tickets p = new Tickets();
            p.setTicketID(result.getInt(1));
            p.setPrice(result.getInt(2));
            p.setDepartureID(result.getInt(3));
            p.setPassengerID(result.getInt(4));
            return p;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Tickets add(Tickets item) {
        String query = "insert into Tickets(TicketID, Price, Departure_ID, Passenger_ID) values ((SELECT (MAX(d.TicketID) + 1) FROM Tickets d), ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getPrice());
            statement.setInt(2,item.getDepartureID());
            statement.setInt(3,item.getPassengerID());
            statement.executeUpdate();
            return item;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Tickets update(Tickets item) {
        String query = "UPDATE Tickets d SET Price = ?, Departure_ID = ?, Passenger_ID = ? WHERE d.TicketID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getPrice());
            statement.setInt(2,item.getDepartureID());
            statement.setInt(3,item.getPassengerID());
            statement.setInt(4,item.getTicketID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Tickets delete(Tickets item) {
        String query = "DELETE FROM Tickets WHERE TicketID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getTicketID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public List<Tickets> getAll() {
        String query = "SELECT * FROM Tickets";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            List<Tickets> list = new ArrayList<>();
            while(result.next()){
                Tickets p = new Tickets();
                p.setTicketID(result.getInt(1));
                p.setPrice(result.getInt(2));
                p.setDepartureID(result.getInt(3));
                p.setPassengerID(result.getInt(4));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    @Override
    public List<Tickets> getByDeparture(int dep) {
        String query = "SELECT * FROM Tickets t WHERE t.Departure_ID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,dep);
            ResultSet result = statement.executeQuery();
            List<Tickets> list = new ArrayList<>();
            while(result.next()){
                Tickets p = new Tickets();
                p.setTicketID(result.getInt(1));
                p.setPrice(result.getInt(2));
                p.setDepartureID(result.getInt(3));
                p.setPassengerID(result.getInt(4));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }}
