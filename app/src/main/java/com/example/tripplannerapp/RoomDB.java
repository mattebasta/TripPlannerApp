package com.example.tripplannerapp;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Trip.class, Shift.class, Stay.class}, version = 17,exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;

    public abstract TripDao tripDao();
    public abstract ShiftDao shiftDao();
    public abstract StayDao stayDao();

    public synchronized static RoomDB getInstance(Context context) {
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, "TripDB")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return database;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(database).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TripDao tripDao;
        private ShiftDao shiftDao;
        private StayDao stayDao;

        private PopulateDbAsyncTask(RoomDB db){
            tripDao = db.tripDao();
            shiftDao = db.shiftDao();
            stayDao = db.stayDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
