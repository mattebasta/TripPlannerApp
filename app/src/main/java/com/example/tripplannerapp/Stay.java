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

    public int getStayID() {
        return StayID;
    }

    public void setStayID(int stayID) {
        StayID = stayID;
    }
}
