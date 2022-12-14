package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Exceptions.StatementException;

import java.time.LocalDateTime;
import java.util.List;

public interface DeparturesDao extends Dao<Departures> {
    /**
     * Searches the DB for departures starting at the specified date
     * @param startDate the date at which the departure takes off
     * @return list of all departures which start at the specified date
     */
    List<Departures> searchByStartDate(LocalDateTime startDate) throws StatementException;

    /**
     * Searches the DB for departures from a certain station
     * @param location the station from where we start
     * @return list of all departures from the specified station
     */
    List<Departures> searchByStation(String location);
    List<Departures> searchByUser(int userID);
    public String getStartCity(int id);
    public String getEndCity(int id);
}
