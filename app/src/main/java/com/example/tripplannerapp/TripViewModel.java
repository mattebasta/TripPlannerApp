package com.example.tripplannerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository repository;
    private LiveData<List<Trip>> allTrip;

    public TripViewModel(@NonNull Application application) {
        super(application);
        repository = new TripRepository(application);
        allTrip = repository.getAllTrip();
    }
    public void update(Trip trip){
        repository.update(trip);
    }
    public void insert(Trip trip){
        repository.insert(trip);
    }
    public void delete(Trip trip){
        repository.delete(trip);
    }
    public void deleteAll(){
        repository.deleteAllTrip();
    }
    public LiveData<List<Trip>> getAllTrip(){
        return allTrip;
    }
}
