package ba.unsa.etf.rpr;

import java.util.Objects;

public class Passengers {
    public Passengers(){

    }
    private String name;
    private String surname;
    private int PassengerID;
    private int TicketID;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passengers that = (Passengers) o;
        return PassengerID == that.PassengerID;
    }

    @Override
    public String toString() {
        return "Passengers{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", PassengerID=" + PassengerID +
                ", TicketID=" + TicketID +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, PassengerID, TicketID);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPassengerID() {
        return PassengerID;
    }

    public void setPassengerID(int passengerID) {
        PassengerID = passengerID;
    }

    public int getTicketID() {
        return TicketID;
    }

    public void setTicketID(int ticketID) {
        TicketID = ticketID;
    }

    public Passengers(String name, String surname, int passengerID, int ticketID) {
        this.name = name;
        this.surname = surname;
        PassengerID = passengerID;
        TicketID = ticketID;
    }
}
