package com.example.tripplannerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StayViewModel extends AndroidViewModel {
    private StayRepository stayRepository;
    private LiveData<List<Stay>> allStay;


    public StayViewModel(@NonNull Application application) {
        super(application);
        stayRepository = new StayRepository(application);
        allStay = stayRepository.getAllStay();
    }
    public void insert(Stay stay){stayRepository.insert(stay);}
    public void update(Stay stay){stayRepository.update(stay);}
    public void delete(Stay stay){stayRepository.delete(stay);}
    public void deleteAll(){stayRepository.deleteAll();}
    public LiveData<List<Stay>> getAllStay(){
        return allStay;
    }
}
