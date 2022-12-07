package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeparturesDaoSQLImplTest {

    @Test
    void getById() {
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        Departures dep = new Departures();
        dep = d.getById(2);
        assertEquals(dep.getDepartureID(), 2);
        assertEquals(dep.getStartDate().toString(), "2023-01-21T15:00");
        assertEquals(dep.getEndStationID(), 3);
        assertEquals(dep.getLength(), "02:30:00");
        assertEquals(dep.getStartTime(), "15:00");
    }

    @Test
    void getById2(){
        //This time we look for key which doesn't exist
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        Departures dep = d.getById(-250);
        assertNull(dep);
    }

    @Test
    void addDeletegetAlltest() {
        //we get all at start, add one object, remove one, and get all again, and should be the same as start
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        List<Departures> before = d.getAll();
        Departures dep = new Departures();
        dep = d.getById(2);
        d.add(dep); //we add the first row again (to the end, it doesn't have to be the same row im just
        dep = d.getById(4); //too lazy to write whole new departures object
        assertEquals(dep.getDepartureID(), 4); //Now we test that the 4th row is indeed there
        assertEquals(dep.getStartDate().toString(), "2023-01-21T15:00");
        assertEquals(dep.getEndStationID(), 3);
        assertEquals(dep.getLength(), "02:30:00");
        assertEquals(dep.getStartTime(), "15:00");
        d.delete(dep); //also serves as delete test, table should remain the same
        List<Departures> after = d.getAll();
        assertEquals(before,after);
    }

    @Test
    void update() {
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        Departures dep = new Departures();
        dep = d.getById(2);
        int temp = dep.getTicketsLeft(); //We save it so we can return it later
        dep.setTicketsLeft(0);
        d.update(dep);
        dep = d.getById(2);
        assertEquals(dep.getTicketsLeft(), 0);
        dep.setTicketsLeft(temp);
        d.update(dep); //we return the DB to what it was before
    }

    @Test
    void searchByStartDate() {
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        Departures dep = new Departures();
        dep = d.getById(1);
        List<Departures> list = d.searchByStartDate(dep.getStartDate());
        assertEquals(list.get(0) ,d.getById(1));
        assertEquals(list.size(), 1);
    }



    @Test
    void searchByStation() {
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        List<Departures> list = d.searchByStation("Sarajevo");
        assertEquals(list.get(0) ,d.getById(1));
        assertEquals(list.get(1), d.getById(2));
    }
    @Test
    void searchByStation2(){
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        List<Departures> list = d.searchByStation("Cazin");
        assertEquals(list.size(), 0); //Nema pruge u cazinu :(
    }
}