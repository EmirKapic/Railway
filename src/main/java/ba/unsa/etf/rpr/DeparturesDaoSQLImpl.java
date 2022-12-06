package ba.unsa.etf.rpr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DeparturesDaoSQLImpl implements DeparturesDao{
    private Connection connection;

    public DeparturesDaoSQLImpl(){

        try {
            this.connection = DriverManager.getConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());;
        }
    }

    @Override
    public Departures getById(int id) {
        return null;
    }

    @Override
    public Departures add(Departures item) {
        return null;
    }

    @Override
    public Departures update(Departures item) {
        return null;
    }

    @Override
    public Departures delete(Departures item) {
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
