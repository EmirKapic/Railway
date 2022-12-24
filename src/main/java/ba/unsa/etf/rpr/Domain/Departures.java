package ba.unsa.etf.rpr.Domain;

import ba.unsa.etf.rpr.IDable;

import java.time.LocalDateTime;
import java.util.Objects;

public class Departures implements IDable {

    public Departures() {
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Departures that = (Departures) o;
        return departureID == that.departureID;
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

    public Departures(int departureID, LocalDateTime startDate, LocalDateTime endDate, String length, int ticketsLeft, int startStationID, int endStationID, int ticketsTotal, String startTime, String endTime) {
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

    public int getID() {
        return departureID;
    }

    public void setID(int departureID) {
        this.departureID = departureID;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
    private LocalDateTime startDate;
    private LocalDateTime endDate;
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
