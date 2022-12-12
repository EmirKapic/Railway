package ba.unsa.etf.rpr;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PassengersDaoSQLImpl implements PassengersDao{
    private Connection connection;
    public PassengersDaoSQLImpl(){
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
    public Passengers getById(int id) {
        String query = "SELECT * FROM Passengers d WHERE d.PassengerID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (!result.next())return null;

            Passengers p = new Passengers();
            p.setPassengerID(result.getInt(1));
            p.setName(result.getString(2));
            p.setSurname(result.getString(3));
            p.setPassword(result.getString(4));
            p.setUsername(result.getString(5));
            return p;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Passengers add(Passengers item) {
        String query = "insert into Passengers(PassengerID, Name, Surname, Password, Username) values ((SELECT (MAX(d.PassengerID) + 1) FROM Passengers d), ?, ?, ?, ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,item.getName());
            statement.setString(2,item.getSurname());
            statement.setString(3,item.getPassword());
            statement.setString(4, item.getUsername());
            statement.executeUpdate();
            return item;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Passengers update(Passengers item) {
        String query = "UPDATE Passengers d SET Name = ?, Surname = ?, Password = ?, Username = ? WHERE d.PassengerID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,item.getName());
            statement.setString(2,item.getSurname());
            statement.setString(3,item.getPassword());
            statement.setString(4,item.getUsername());
            statement.setInt(5,item.getPassengerID());

            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Passengers delete(Passengers item) {
        String query = "DELETE FROM Passengers WHERE PassengerID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getPassengerID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }

    @Override
    public List<Passengers> getAll() {
        String query = "SELECT * FROM Passengers";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            List<Passengers> list = new ArrayList<>();
            while(result.next()){
                Passengers p = new Passengers();
                p.setPassengerID(result.getInt(1));
                p.setName(result.getString(2));
                p.setSurname(result.getString(3));
                p.setPassword(result.getString(4));
                p.setUsername(result.getString(5));
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tickets> getAllTickets(int passengerID) {
        String query = "SELECT * FROM Tickets WHERE Passenger_ID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,passengerID);
            ResultSet result = statement.executeQuery();
            List<Tickets> list = new ArrayList<>();
            while (result.next()){
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

    public Passengers getByUsername(String username){
        String query = "SELECT * FROM Passengers WHERE Username = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,username);
            ResultSet result = statement.executeQuery();
            if (!result.next())return null;
            Passengers p = new Passengers();
            p.setPassengerID(result.getInt(1));
            p.setName(result.getString(2));
            p.setSurname(result.getString(3));
            p.setPassword(result.getString(4));
            p.setUsername(result.getString(5));
            return p;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
