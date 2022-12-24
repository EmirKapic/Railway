package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Tickets;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class TicketsDaoSQLImpl extends AbstractDao<Tickets> implements TicketsDao{

    public TicketsDaoSQLImpl(){
        super("Tickets");
    }

    @Override
    public Tickets row2object(ResultSet rs) throws SQLException {
        Tickets t = new Tickets();
        t.setID(rs.getInt("ID"));
        t.setPrice(rs.getInt("Price"));
        t.setDep(rs.getInt("Departure_ID"));
        t.setPassengerID(rs.getInt("Passenger_ID"));
        return t;
    }

    @Override
    public Map<String, Object> object2row(Tickets item) {
        Map<String, Object> row = new TreeMap<>();
        row.put("ID", item.getID());
        row.put("Price", item.getPrice());
        row.put("Departure_ID", item.getDepartureID());
        row.put("Passenger_ID", item.getPassengerID());
        return row;
    }

    @Override
    public List<Tickets> getByDeparture(int dep) {
        String query = "SELECT * FROM Tickets t WHERE t.Departure_ID = ?";
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1,dep);
            ResultSet result = statement.executeQuery();
            List<Tickets> list = new ArrayList<>();
            while(result.next()){
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


}
