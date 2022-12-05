package ba.unsa.etf.rpr;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Departures {
    public Departures(int departureID, Date startDate, Date endDate, String length, int ticketsLeft, String startStation, String endStation, int ticketsTotal) {
        DepartureID = departureID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.length = length;
        this.ticketsLeft = ticketsLeft;
        this.startStation = startStation;
        this.endStation = endStation;
        this.ticketsTotal = ticketsTotal;
    }

    public int getDepartureID() {
        return DepartureID;
    }

    public void setDepartureID(int departureID) {
        DepartureID = departureID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Departures{" +
                "DepartureID=" + DepartureID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", length='" + length + '\'' +
                ", ticketsLeft=" + ticketsLeft +
                ", startStation='" + startStation + '\'' +
                ", endStation='" + endStation + '\'' +
                ", ticketsTotal=" + ticketsTotal +
                '}';
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    private int DepartureID;
    private Date startDate;
    private Date endDate;
    private String length;
    private int ticketsLeft;
    private String startStation;
    private String endStation;
    private int ticketsTotal;

    @Override
    public int hashCode() {
        return Objects.hash(DepartureID,startDate,endDate,length,ticketsLeft,ticketsTotal, startStation, endStation);
    }
}
