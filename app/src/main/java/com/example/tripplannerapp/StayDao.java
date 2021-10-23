package com.example.tripplannerapp;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;


@Dao
public interface StayDao {
    //insert
    @Insert(onConflict = REPLACE)
    void insert(Stay stay);

    //delete single stay
    @Delete
    void delete(Stay stay);

    //delete all
    @Query("DELETE FROM stay")
    void deleteAllStay();

    //Update
    @Update
    void update(Stay stay);

    //getAll
    @Query("SELECT * From stay ORDER BY ID ASC ")
    LiveData<List<Stay>> getAllStay();

    //get Latitude and Longitude of the stay
    @Query("SELECT StayLatitude, StayLongitude FROM Stay")
    LiveData<List<stayCoordinates>> getStayCoordinates();

     static class stayCoordinates{
        public Double StayLatitude;
        public Double StayLongitude;
    }
}