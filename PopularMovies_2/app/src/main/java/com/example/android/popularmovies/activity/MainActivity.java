package com.example.android.popularmovies.activity;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.MovieAdapter;
import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.database.AppDatabase;
import com.example.android.popularmovies.utils.MovieDataJsonUtils;
import com.example.android.popularmovies.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_recyclerView) RecyclerView mRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private MovieAdapter adapter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        URL url = null;
        try {
            url = NetworkUtils.buildUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        new MovieDbQueryTask().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.toString().equals(getString(R.string.favorites))) {
            updateUiForFavorites();
        } else {
            URL url = null;
            try {
                url = NetworkUtils.sortMovies(item.toString());
            } catch (MalformedURLException e) {
            }
            new MovieDbQueryTask().execute(url);
        }
        return true;
    }

    private void updateUiForFavorites() {
        final LiveData<Movie[]> movies = AppDatabase.getInstance(this).getMovieDao().getAllMovies();
        movies.observe(this, new Observer<Movie[]>() {
            @Override
            public void onChanged(@Nullable Movie[] movies) {
                Log.d(TAG, "onChanged: updating UI for Favorites");
                new MovieDbQueryTask().onPostExecute(movies);
            }
        });
    }

    private class MovieDbQueryTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(URL... urls) {
            if (urls.length == 0) {
                return null;
            }

            URL url = urls[0];

            try {
                String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(url);

                return MovieDataJsonUtils.getMoviesFromJson(responseFromHttpUrl);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            adapter = new MovieAdapter(movies);
            mRecyclerView.setAdapter(adapter);
        }
    }

}
