package com.example.tripplannerapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.List;

public class StayRepository {
    private StayDao stayDao;
    private LiveData<List<Stay>> allStay;
    private LiveData<List<StayDao.stayCoordinates>> stayCoordinates;

    public StayRepository(Application application){
        RoomDB database = RoomDB.getInstance(application);
        stayDao = database.stayDao();
        allStay = stayDao.getAllStay();
        stayCoordinates = stayDao.getStayCoordinates();
    }

    public void insert(Stay stay){new InsertStayAsyncTask(stayDao).execute(stay);}

    public void update(Stay stay){new UpdateStayAsyncTask(stayDao).execute(stay);}

    public void delete(Stay stay){new DeleteStayAsyncTask(stayDao).execute(stay);}

    public void deleteAll(){new DeleteAllStayAsyncTask(stayDao).execute();}

    public LiveData<List<StayDao.stayCoordinates>> getStayCoordinates(){return stayCoordinates;}

    public LiveData<List<Stay>> getAllStay(){ return allStay;}

    public static class InsertStayAsyncTask extends AsyncTask<Stay, Void, Void>{
        private StayDao stayDao;

        private InsertStayAsyncTask(StayDao stayDao){
            this.stayDao = stayDao;
        }

        @Override
        protected Void doInBackground(Stay... stays) {
            stayDao.insert(stays[0]);
            return null;
        }
    }

    public static class UpdateStayAsyncTask extends AsyncTask<Stay,Void,Void>{
        private StayDao stayDao;

        private UpdateStayAsyncTask(StayDao stayDao){
            this.stayDao = stayDao;
        }

        @Override
        protected Void doInBackground(Stay... stays) {
            stayDao.update(stays[0]);
            return null;
        }
    }

    public static class DeleteStayAsyncTask extends AsyncTask<Stay,Void,Void>{
        private StayDao stayDao;

        private DeleteStayAsyncTask(StayDao stayDao){
            this.stayDao = stayDao;
        }

        @Override
        protected Void doInBackground(Stay... stays) {
            stayDao.delete(stays[0]);
            return null;
        }
    }

    private static class DeleteAllStayAsyncTask extends AsyncTask<Void, Void, Void> {
        private StayDao stayDao;

        private DeleteAllStayAsyncTask(StayDao stayDao){
            this.stayDao = stayDao;

        }
        @Override
        protected Void doInBackground(Void...voids) {
            stayDao.deleteAllStay();
            return null;
        }
    }
}
