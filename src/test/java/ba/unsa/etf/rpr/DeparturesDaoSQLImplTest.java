package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeparturesDaoSQLImplTest {

    @Test
    void getById() {
        DeparturesDaoSQLImpl d = new DeparturesDaoSQLImpl();
        Departures dep = new Departures();
        dep = d.getById(2);
        assertEquals(dep.getDepartureID(), 2);
        assertEquals(dep.getStartDate().toString(), "2023-01-21");
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
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAll() {
    }

    @Test
    void searchByStartDate() {
    }

    @Test
    void searchByStation() {
    }
}