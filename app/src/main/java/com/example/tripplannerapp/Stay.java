package com.example.tripplannerapp;

public class Stay {
    private String stayPlace;
    private String fromDate;
    private String toDate;

    public Stay(String stayPlace, String fromDate, String toDate){
        this.stayPlace = stayPlace;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getStayPlace() {
        return stayPlace;
    }

    public void setStayPlace(String stayPlace) {
        this.stayPlace = stayPlace;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
