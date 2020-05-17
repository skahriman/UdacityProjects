package com.example.android.popularmovies.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.android.popularmovies.data.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie ORDER BY vote_average")
    LiveData<Movie[]> getAllMovies();

    @Query("SELECT * FROM movie WHERE id = :movieId")
    Movie getMovie(String movieId);

    @Insert
    void addMovie(Movie movie);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMovie(Movie movie);

    @Delete
    void deleteMovie(Movie movie);

}


