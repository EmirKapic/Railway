package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Departures;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Mysql implementation of DeparturesDao
 */
public class DeparturesDaoSQLImpl extends AbstractDao<Departures> implements DeparturesDao{

    public DeparturesDaoSQLImpl(){
        super("Departures");
    }

    @Override
    public Departures row2object(ResultSet result) {
        try {
            Departures dep = new Departures();
            dep.setID(result.getInt("ID"));
            dep.setStartDate(result.getObject("Start_date", LocalDateTime.class));
            dep.setEndDate(result.getObject("End_date", LocalDateTime.class));
            dep.setLength(result.getString("Length"));
            dep.setTicketsLeft(result.getInt("Tickets_left"));
            dep.setStartStationID(result.getInt("Start_station_ID"));
            dep.setEndStationID(result.getInt("End_station_ID"));
            dep.setTicketsTotal(result.getInt("Tickets_total"));
            dep.setStartTime(dep.getStartDate().toString().split("T")[1]);
            dep.setEndTime(dep.getEndDate().toString().split("T")[1]);
            return dep;
        } catch (SQLException e) {
            System.out.println("Erorr while loading into object from row");
            System.exit(1);
        }
        return null;
    }

    @Override
    public Map<String, Object> object2row(Departures item) {
        Map<String, Object> row = new TreeMap<>();
        row.put("ID", item.getID());
        row.put("Start_date", item.getStartDate());
        row.put("End_date", item.getEndDate());
        row.put("Length", item.getLength());
        row.put("Tickets_left", item.getTicketsLeft());
        row.put("Start_station_ID", item.getStartStationID());
        row.put("End_station_ID", item.getEndStationID());
        row.put("Tickets_total", item.getTicketsTotal());
        return row;
    }

    @Override
    public List<Departures> searchByStartDate(LocalDateTime startDate) {
        List<Departures> list = new ArrayList<>();
        String query = "SELECT * FROM Departures d WHERE d.Start_date = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setObject(1,startDate);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Departures dep = new Departures();
                dep.setDepartureID(result.getInt(1));
                dep.setStartDate(result.getObject(2, LocalDateTime.class));
                dep.setEndDate(result.getObject(3, LocalDateTime.class));
                dep.setLength(result.getString(4));
                dep.setTicketsLeft(result.getInt(5));
                dep.setStartStationID(result.getInt(6));
                dep.setEndStationID(result.getInt(7));
                dep.setTicketsTotal(result.getInt(8));
                dep.setStartTime(dep.getStartDate().toString().split("T")[1]);
                dep.setEndTime(dep.getEndDate().toString().split("T")[1]);
                list.add(dep);
            }
            return list;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Departures> searchByStation(String location) {
        List<Departures> list = new ArrayList<>();
        String query = "SELECT d.* FROM Departures d WHERE d.Start_station_ID = ANY (SELECT ts.Train_stations_ID FROM Train_stations ts WHERE ts.Location = ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,location);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                Departures dep = new Departures();
                dep.setDepartureID(result.getInt(1));
                dep.setStartDate(result.getObject(2, LocalDateTime.class));
                dep.setEndDate(result.getObject(3, LocalDateTime.class));
                dep.setLength(result.getString(4));
                dep.setTicketsLeft(result.getInt(5));
                dep.setStartStationID(result.getInt(6));
                dep.setEndStationID(result.getInt(7));
                dep.setTicketsTotal(result.getInt(8));
                dep.setStartTime(dep.getStartDate().toString().split("T")[1]);
                dep.setEndTime(dep.getEndDate().toString().split("T")[1]);
                list.add(dep);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
