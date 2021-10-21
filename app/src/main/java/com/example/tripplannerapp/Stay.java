package com.example.tripplannerapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Stay")
public class Stay implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int StayID;

    @ColumnInfo(name = "StayPlace")
    private String stayPlace;

    @ColumnInfo(name = "StayFromDate")
    private String fromDate;

    @ColumnInfo(name = "StayToDate")
    private String toDate;

    @ColumnInfo(name = "StayLongitude")
    private Double stayLongitude;

    @ColumnInfo(name = "StayLatitude")
    private Double stayLatitude;

    public Stay(String stayPlace, String fromDate, String toDate, Double stayLongitude, Double stayLatitude){
        this.stayPlace = stayPlace;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.stayLongitude = stayLongitude;
        this.stayLatitude = stayLatitude;
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

    public int getStayID() {
        return StayID;
    }

    public void setStayID(int stayID) {
        StayID = stayID;
    }

    public Double getStayLongitude() {
        return stayLongitude;
    }

    public void setStayLongitude(Double stayLongitude) {
        this.stayLongitude = stayLongitude;
    }

    public Double getStayLatitude() {
        return stayLatitude;
    }

    public void setStayLatitude(Double stayLatitude) {
        this.stayLatitude = stayLatitude;
    }
}
