package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.Domain.Tickets;

import java.util.List;

public interface TicketsDao extends Dao<Tickets>{
    /**
     * Finds all tickets which belong to a certain departure
     * @param dep the departure for which we want tickets
     * @return list of tickets which are going to a departure
     */
    public List<Tickets> getByDeparture(int dep);
}
