package ba.unsa.etf.rpr.Business;

import ba.unsa.etf.rpr.Dao.DaoFactory;
import ba.unsa.etf.rpr.Domain.Departures;
import ba.unsa.etf.rpr.Domain.Passengers;
import ba.unsa.etf.rpr.Domain.Tickets;
import ba.unsa.etf.rpr.Exceptions.StatementException;

import java.util.List;

public class PassengerManager {
    public List<Passengers> getAll() throws StatementException {
        return DaoFactory.passengersDao().getAll();
    }
    public Passengers getByID(int id) throws StatementException {
        return DaoFactory.passengersDao().getById(id);
    }
    public Passengers add(Passengers p) throws StatementException {
        return DaoFactory.passengersDao().add(p);
    }
    public Passengers update(Passengers p) throws StatementException {
        return DaoFactory.passengersDao().update(p);
    }
    public void delete(int id) throws StatementException {
        DaoFactory.passengersDao().delete(id);
    }
    public List<Tickets> getAllTickets(int id){
        return DaoFactory.passengersDao().getAllTickets(id);
    }
    public Passengers getByUsername(String username){
        return DaoFactory.passengersDao().getByUsername(username);
    }

}
