package com.example.tripplannerapp;

import java.util.Date;

public class Trip {

    private String tripName;
    private String tripDesc;
    private String startDate;
    private String endDate;

    public Trip(String tripName, String tripDesc, String startDate, String endDate) {
        this.tripName = tripName;
        this.tripDesc = tripDesc;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getTripDesc() {
        return tripDesc;
    }

    public void setTripDesc(String tripDesc) {
        this.tripDesc = tripDesc;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
