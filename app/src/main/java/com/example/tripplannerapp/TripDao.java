package com.example.tripplannerapp;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TripDao {
    //insert a trip
    @Insert(onConflict = REPLACE)
    void insert(Trip trip);

    //delete single trip
    @Delete
    void delete(Trip trip);

    //delete all
    @Query("DELETE FROM trip")
    void deleteAllTrip();

    //Update
    @Update
    void update(Trip trip);

    //getAll
    @Query("SELECT * From trip")
    LiveData<List<Trip>> getAllTrip();
}