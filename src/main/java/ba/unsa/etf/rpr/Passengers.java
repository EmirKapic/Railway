package ba.unsa.etf.rpr;

import java.util.Objects;

public class Passengers {
    public Passengers(){

    }
    private String name;
    private String surname;
    private int PassengerID;
    private String password;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, PassengerID);
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


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Passengers( int passengerID, String name, String surname,String password, String username) {
        this.name = name;
        this.surname = surname;
        PassengerID = passengerID;
        this.password=password;
        this.username = username;
    }
}
