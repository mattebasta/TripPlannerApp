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

    @ColumnInfo(name = "LatitudeDep")
    private Double LatitudeDep;

    @ColumnInfo(name = "LongitudeDep")
    private Double LongitudeDep;

    @ColumnInfo(name = "LatitudeArr")
    private Double LatitudeArr;

    @ColumnInfo(name = "LongitudeArr")
    private Double LongitudeArr;

    public Shift(String departure, String arrive, String dateOfShift, Double LatitudeDep, Double LongitudeDep, Double LatitudeArr, Double LongitudeArr) {
        this.departure = departure;
        this.arrive = arrive;
        this.dateOfShift = dateOfShift;
        this.LatitudeDep = LatitudeDep;
        this.LongitudeDep = LongitudeDep;
        this.LatitudeArr = LatitudeArr;
        this.LongitudeArr = LongitudeArr;
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

    public Double getLatitudeDep() {
        return LatitudeDep;
    }

    public void setLatitudeDep(Double latitudeDep) {
        LatitudeDep = latitudeDep;
    }

    public Double getLongitudeDep() {
        return LongitudeDep;
    }

    public void setLongitudeDep(Double longitudeDep) {
        LongitudeDep = longitudeDep;
    }

    public Double getLatitudeArr() {
        return LatitudeArr;
    }

    public void setLatitudeArr(Double latitudeArr) {
        LatitudeArr = latitudeArr;
    }

    public Double getLongitudeArr() {
        return LongitudeArr;
    }

    public void setLongitudeArr(Double longitudeArr) {
        LongitudeArr = longitudeArr;
    }
}
