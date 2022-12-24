package ba.unsa.etf.rpr.Domain;

import java.util.Objects;

public class Tickets {
    private int ticketID;
    private int price;
    private int dep;
    private int passengerID;

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", DepartureID=" + dep +
                '}';
    }
    public Tickets(){

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tickets tickets = (Tickets) o;
        return ticketID == tickets.ticketID;
    }

    public int getPassengerID() {
        return passengerID;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public int getDep() {
        return dep;
    }

    public void setDep(int dep) {
        this.dep = dep;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, price, dep);
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDepartureID() {
        return dep;
    }

    public void setDepartureID(int departureID) {
        this.dep = departureID;
    }

    public Tickets(int ticketID, int price, int departureID, int passengerID) {
        this.ticketID = ticketID;
        this.price = price;
        this.dep = departureID;
        this.passengerID = passengerID;
    }
}
