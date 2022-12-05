package ba.unsa.etf.rpr;

import java.util.Objects;

public class Tickets {
    private int ticketID;
    private int price;
    private int DepartureID;

    @Override
    public String toString() {
        return "Tickets{" +
                "ticketID=" + ticketID +
                ", price=" + price +
                ", DepartureID=" + DepartureID +
                '}';
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
        return Objects.hash(ticketID, price, DepartureID);
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
        return DepartureID;
    }

    public void setDepartureID(int departureID) {
        DepartureID = departureID;
    }

    public Tickets(int ticketID, int price, int departureID) {
        this.ticketID = ticketID;
        this.price = price;
        DepartureID = departureID;
    }
}
