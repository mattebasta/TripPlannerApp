package com.example.tripplannerapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Update;

import java.util.List;

public class ShiftRepository {
    private ShiftDao shiftDao;
    private LiveData<List<Shift>> allShift;

    public ShiftRepository(Application application){
        RoomDB database = RoomDB.getInstance(application);
        shiftDao = database.shiftDao();
        allShift = shiftDao.getAllShift();
    }

    public void insert(Shift shift){
        new InsertShiftAsyncTask(shiftDao).execute(shift);
    }

    public void update(Shift shift){
        new UpdateShiftAsyncTask(shiftDao).execute(shift);
    }

    public void delete(Shift shift){
        new DeleteShiftAsyncTask(shiftDao).execute(shift);
    }

    public void deleteAllShift(){
        new DeleteAllShiftAsyncTask(shiftDao).execute();
    }

    public  LiveData<List<Shift>> getAllShift(){return allShift;}

    private static class InsertShiftAsyncTask extends AsyncTask<Shift, Void, Void>{
        private ShiftDao shiftDao;

        private InsertShiftAsyncTask(ShiftDao shiftDao){
            this.shiftDao = shiftDao;
        }

        @Override
        protected Void doInBackground(Shift... shifts) {
            shiftDao.insert(shifts[0]);
            return null;
        }
    }

    private static class UpdateShiftAsyncTask extends AsyncTask<Shift, Void, Void>{
        private ShiftDao shiftDao;

        private UpdateShiftAsyncTask(ShiftDao shiftDao){
            this.shiftDao = shiftDao;
        }

        @Override
        protected Void doInBackground(Shift... shifts) {
            shiftDao.update(shifts[0]);
            return null;
        }
    }

    private static class DeleteShiftAsyncTask extends AsyncTask<Shift, Void, Void>{
        private ShiftDao shiftDao;

        private DeleteShiftAsyncTask(ShiftDao shiftDao){
            this.shiftDao = shiftDao;
        }

        @Override
        protected Void doInBackground(Shift... shifts) {
            shiftDao.delete(shifts[0]);
            return null;
        }
    }

    private static class DeleteAllShiftAsyncTask extends AsyncTask<Void, Void, Void>{
        private ShiftDao shiftDao;

        private DeleteAllShiftAsyncTask(ShiftDao shiftDao){
            this.shiftDao = shiftDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            shiftDao.deleteAllShift();
            return null;
        }
    }


}
