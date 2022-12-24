package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.TrainStations;

import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TrainStationsDAOSQLImpl implements TrainStationsDao{

    private Connection connection;

    public TrainStationsDAOSQLImpl()  {
        try {
            FileReader reader = new FileReader("D:\\Java Projects\\Railway\\src\\main\\resources\\db.properties");
            Properties p = new Properties();
            p.load(reader);
            this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public TrainStations getById(int id) {
        String query = "SELECT * FROM Train_stations WHERE Train_stations_ID = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) return null;

            TrainStations t = new TrainStations();
            t.setTrainStationID(resultSet.getInt(1));
            t.setLocation(resultSet.getString(2));
            return t;

        } catch (SQLException e) {
            System.out.println("ERROR: Couldn't get by ID!");
        }
        return null;
    }

    @Override
    public TrainStations add(TrainStations item) {
        String query = "insert into Train_stations(Train_station_ID, Location) values ((SELECT (MAX(d.Train_station_ID) + 1) FROM Train_stations d), ?)";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setString(1,item.getLocation());
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // String query = "insert into Tickets(TicketID, Price, Departure_ID, Passenger_ID) values ((SELECT (MAX(d.TicketID) + 1) FROM Tickets d), ?, ?, ?)";
        return null;
    }

    /**
     * this function should never be used(a train station can't change its location..)
     * @param item nil
     * @return nil
     */
    @Override
    public TrainStations update(TrainStations item) {
        return null;
    }

    @Override
    public TrainStations delete(TrainStations item) {
        String query = "DELETE FROM Train_stations WHERE Train_station_id = ?";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            statement.setInt(1,item.getTrainStationID());
            statement.executeUpdate();
            return item;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<TrainStations> getAll() {
        String query = "SELECT * FROM Train_stations";
        try {
            PreparedStatement statement = this.connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            List<TrainStations> L = new ArrayList<>();
            while (resultSet.next()){
                TrainStations t = new TrainStations();
                t.setTrainStationID(resultSet.getInt(1));
                t.setLocation(resultSet.getString(2));
                L.add(t);
            }
            return L;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Currently not needed
     * @param location NIL
     * @return NIL
     */
    @Override
    public TrainStations findByLocation(String location) {
        return null;
    }
}
