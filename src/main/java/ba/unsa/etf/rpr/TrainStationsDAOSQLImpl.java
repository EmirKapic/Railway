package ba.unsa.etf.rpr;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class TrainStationsDAOSQLImpl implements TrainStationsDao{

    private Connection connection;

    public TrainStationsDAOSQLImpl() throws IOException {
        FileReader reader = new FileReader("D:\\Java Projects\\Railway\\src\\main\\resources\\db.properties");
        Properties p = new Properties();
        p.load(reader);
        try {
            this.connection = DriverManager.getConnection(p.getProperty("url"), p.getProperty("user"), p.getProperty("password"));
        } catch (SQLException e) {
            System.out.println("WARNING: Couldn't connect to database!");
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
        return null;
    }

    @Override
    public TrainStations update(TrainStations item) {
        return null;
    }

    @Override
    public TrainStations delete(TrainStations item) {
        return null;
    }

    @Override
    public List<TrainStations> getAll() {
        return null;
    }

    @Override
    public TrainStations findByLocation(String location) {
        return null;
    }
}
