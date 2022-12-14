package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.TrainStations;

public interface TrainStationsDao extends Dao<TrainStations>{
    /**
     * Finds the station at the specified city, if there is one
     * @param location city we're looking for
     * @return the train station located in the city, if one exists
     */
    public TrainStations findByLocation(String location);
}
