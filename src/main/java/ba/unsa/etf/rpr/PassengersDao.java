package ba.unsa.etf.rpr;

import java.util.List;

public interface PassengersDao extends Dao<Passengers>{
    //public List<Passengers> searchByName(String name); SHOULD NOT BE USED! People can have the same name which can easily create problems. Use ID only
    List<Tickets> getAllTickets(int PassengerID);
}
