package com.example.tripplannerapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShiftViewModel extends AndroidViewModel {
    private ShiftRepository shiftRepository;
    private LiveData<List<Shift>> allShift;

    public ShiftViewModel(@NonNull Application application) {
        super(application);
        shiftRepository = new ShiftRepository(application);
        allShift = shiftRepository.getAllShift();
    }
    public void insert(Shift shift){shiftRepository.insert(shift);}
    public void update(Shift shift){shiftRepository.update(shift);}
    public void delete(Shift shift){shiftRepository.delete(shift);}
    public void deleteAll(){shiftRepository.deleteAllShift();}
    public  LiveData<List<Shift>> getAllShift(){return allShift;}

}
