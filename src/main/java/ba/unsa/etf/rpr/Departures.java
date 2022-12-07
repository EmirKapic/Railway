package ba.unsa.etf.rpr;

import java.util.Date;
import java.util.Objects;

public class Departures {

    public Departures() {
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Departures(int departureID, Date startDate, Date endDate, String length, int ticketsLeft, int startStationID, int endStationID, int ticketsTotal, String startTime, String endTime) {
        this.departureID = departureID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.length = length;
        this.ticketsLeft = ticketsLeft;
        this.startStationID = startStationID;
        this.endStationID = endStationID;
        this.ticketsTotal = ticketsTotal;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getDepartureID() {
        return departureID;
    }

    public void setDepartureID(int departureID) {
        this.departureID = departureID;
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
                "DepartureID=" + departureID +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", length='" + length + '\'' +
                ", ticketsLeft=" + ticketsLeft +
                ", startStation='" + startStationID + '\'' +
                ", endStation='" + endStationID + '\'' +
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

    public int getStartStationID() {
        return startStationID;
    }

    public void setStartStationID(int startStationID) {
        this.startStationID = startStationID;
    }

    public int getEndStationID() {
        return endStationID;
    }

    public void setEndStationID(int endStationID) {
        this.endStationID = endStationID;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    private int departureID;
    private Date startDate;
    private Date endDate;
    private String length;
    private int ticketsLeft;
    private int startStationID;
    private int endStationID;
    private int ticketsTotal;

    private String startTime;
    private String endTime;

    @Override
    public int hashCode() {
        return Objects.hash(departureID,startDate,endDate,length,ticketsLeft,ticketsTotal, startStationID, endStationID);
    }
}
