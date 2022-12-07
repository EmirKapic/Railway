package ba.unsa.etf.rpr;

import java.util.Objects;

public class Tickets {
    private int ticketID;
    private int price;
    private Departures dep;

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
        return dep.getDepartureID();
    }

    public void setDepartureID(int departureID) {
        dep.setDepartureID(departureID);
    }

    public Tickets(int ticketID, int price, Departures departureID) {
        this.ticketID = ticketID;
        this.price = price;
        this.dep = departureID;
    }
}
