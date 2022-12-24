package ba.unsa.etf.rpr.Dao;

public class DaoFactory {
    private static final PassengersDao passDao = new PassengersDaoSQLImpl();
    private static final TicketsDaoSQLImpl ticketsDao = new TicketsDaoSQLImpl();
    private static final DeparturesDaoSQLImpl depDao = new DeparturesDaoSQLImpl();
    private static final TrainStationsDAOSQLImpl tsDao = new TrainStationsDAOSQLImpl();

    private DaoFactory(){}

    public static PassengersDao passengersDao(){
        return passDao;
    }
    public static TicketsDao ticketsDao(){
        return ticketsDao;
    }
    public static DeparturesDao departuresDao(){
        return depDao;
    }
    public static TrainStationsDao trainStationsDao(){
        return tsDao;
    }


}
