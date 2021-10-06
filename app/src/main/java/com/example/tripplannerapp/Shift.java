package com.example.tripplannerapp;

public class Shift {
    private String departure;
    private String arrive;
    private String dateOfShift;

    public Shift(String departure, String arrive, String dateOfShift) {
        this.departure = departure;
        this.arrive = arrive;
        this.dateOfShift = dateOfShift;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public String getDateOfShift() {
        return dateOfShift;
    }

    public void setDateOfShift(String dateOfShift) {
        this.dateOfShift = dateOfShift;
    }

}
