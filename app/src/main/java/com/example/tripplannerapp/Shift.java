package com.example.tripplannerapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Shift")
public class Shift implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int ShiftID;

    @ColumnInfo(name = "ShiftDeparture")
    private String departure;

    @ColumnInfo(name = "ShiftArrive")
    private String arrive;

    @ColumnInfo(name = "ShiftDate")
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

    public int getShiftID() {
        return ShiftID;
    }

    public void setShiftID(int shiftID) {
        ShiftID = shiftID;
    }

}
