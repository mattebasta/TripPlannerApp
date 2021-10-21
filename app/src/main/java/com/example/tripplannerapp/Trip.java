package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;


@Entity(tableName = "Trip")
public class Trip implements Serializable {


    @NonNull
    @PrimaryKey
    private String tripName;

    @ColumnInfo(name = "TheTripDesc")
    private String tripDesc;

    @ColumnInfo(name = "TheTripStart")
    private String startDate;

    @ColumnInfo(name = "TheTripEnd")
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
