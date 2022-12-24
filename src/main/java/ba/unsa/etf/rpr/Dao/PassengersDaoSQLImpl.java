package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Domain.Tickets;
import ba.unsa.etf.rpr.Exceptions.StatementException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class PassengersDaoSQLImpl extends AbstractDao<Passengers> implements PassengersDao{
    public PassengersDaoSQLImpl(){
        super("Passengers");
    }

    @Override
    public Passengers row2object(ResultSet rs) throws SQLException {
        Passengers p = new Passengers();
        p.setID(rs.getInt("ID"));
        p.setName(rs.getString("Name"));
        p.setSurname(rs.getString("Surname"));
        p.setPassword(rs.getString("Password"));
        p.setUsername(rs.getString("Username"));
        return p;
    }

    @Override
    public Map<String, Object> object2row(Passengers item) {
        Map<String, Object> row = new TreeMap<>();
        row.put("ID", item.getID());
        row.put("Name", item.getName());
        row.put("Surname",item.getSurname());
        row.put("Password", item.getPassword());
        row.put("Username", item.getUsername());
        return row;
    }

    @Override
    public List<Tickets> getAllTickets(int passengerID) {
        String query = "SELECT * FROM Tickets WHERE Passenger_ID = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1,passengerID);
            ResultSet result = statement.executeQuery();
            List<Tickets> list = new ArrayList<>();
            while (result.next()){
                Tickets p = new Tickets();
                p.setID(result.getInt(1));
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
        List<Passengers> allPass = null;
        try {
            allPass = this.getAll();
        } catch (StatementException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        for(Passengers p : allPass){
            if (p.getUsername().equals(username))
                return p;
        }
        return null;
    }


}
