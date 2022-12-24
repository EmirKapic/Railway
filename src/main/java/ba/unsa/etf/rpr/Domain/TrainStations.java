package ba.unsa.etf.rpr.Domain;

import java.util.Objects;

public class TrainStations {
    private int trainStationID;
    private String location;

    public TrainStations(){

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrainStations that = (TrainStations) o;
        return trainStationID == that.trainStationID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trainStationID, location);
    }

    @Override
    public String toString() {
        return "TrainStations{" +
                "trainStationID=" + trainStationID +
                ", location='" + location + '\'' +
                '}';
    }

    public int getTrainStationID() {
        return trainStationID;
    }

    public void setTrainStationID(int trainStationID) {
        this.trainStationID = trainStationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TrainStations(int trainStationID, String location) {
        this.trainStationID = trainStationID;
        this.location = location;
    }
}
