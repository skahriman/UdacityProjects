package com.example.android.popularmovies.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.util.Log;

import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.utils.Converters;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final String TAG = "AppDatabase";
    private static final String DATABASE_NAME = "movie";
    public static final Object LOCK = new Object();
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                Log.d(TAG, "getInstance: Creating new database instance");
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
//                        //TODO: this is temporary place for saving operation
//                        .allowMainThreadQueries()
                        .build();
            }
        }
        Log.d(TAG, "getInstance: Getting the database instance");
        return sInstance;
    }
    public abstract MovieDao getMovieDao();
}
