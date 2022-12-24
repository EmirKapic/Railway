package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.TrainStations;

import java.sql.*;
import java.util.*;

public class TrainStationsDAOSQLImpl extends AbstractDao<TrainStations> implements TrainStationsDao{

    public TrainStationsDAOSQLImpl(){
        super("Train_stations");
    }

    @Override
    public TrainStations row2object(ResultSet rs) throws SQLException {
        TrainStations t = new TrainStations();
        t.setID(rs.getInt("ID"));
        t.setLocation(rs.getString("Location"));
        return t;
    }

    @Override
    public Map<String, Object> object2row(TrainStations item) {
        Map<String, Object> row = new TreeMap<>();
        row.put("ID", item.getID());
        row.put("Location", item.getLocation());
        return row;
    }

    @Override
    public TrainStations findByLocation(String location) {
        return null;
    }
}
