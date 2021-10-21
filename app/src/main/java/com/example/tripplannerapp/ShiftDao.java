package com.example.tripplannerapp;


import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ShiftDao {
    //Insert new shift
    @Insert(onConflict = REPLACE)
    void insert(Shift shift);

    //delete single shift
    @Delete
    void delete(Shift shift);

    //delete all
    @Query("DELETE FROM shift")
    void deleteAllShift();

    //Update
    @Update
    void update(Shift shift);

    //getAll
    @Query("SELECT * From shift ORDER BY ShiftID ASC")
    LiveData<List<Shift>> getAllShift();

    //get Latitude and Longitude of the shift Departure
    @Query("SELECT LatitudeDep, LongitudeDep FROM Shift")
    LiveData<List<shiftDepCoordinates>> getShiftDepCoords();

    //get Latitude and Longitude of the shift Arrive
    @Query("SELECT LatitudeArr, LongitudeArr FROM Shift")
    LiveData<List<shiftArrCoordinates>> getShiftArrCoords();

    static class shiftDepCoordinates{
        public Double LatitudeDep;
        public Double LongitudeDep;
    }

    static class shiftArrCoordinates{
        public Double LatitudeArr;
        public Double LongitudeArr;
    }
}
