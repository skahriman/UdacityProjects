package com.exmaple.android.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import com.exmaple.android.popularmovies.adapter.RecyclerViewAdapter;
import com.exmaple.android.popularmovies.data.Movie;
import com.exmaple.android.popularmovies.utils.MovieDataJsonUtils;
import com.exmaple.android.popularmovies.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //TODO remove this api key when you publish your app
    final private static String API_KEY =
            "https://api.themoviedb.org/3/discover/movie?api_key=3ba8d51a5df2e04fe0ffedf1e9a8eec4";
    private static final String TAG = "MainActivity";

    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    RecyclerViewAdapter adapter;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_recyclerView);
        mGridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mImageView = findViewById(R.id.iv_image);

        URL url = null;
        try {
            url = new URL(API_KEY);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new MovieDbQueryTask().execute(url);

    }

    public class MovieDbQueryTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(URL... urls) {
            Log.d("MainActivity", "doInBackground: ");
            if (urls.length == 0) {
                return null;
            }

            URL url = urls[0];

            try {
                String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(url);
                Movie[] movies = MovieDataJsonUtils.getMoviesFromJson(MainActivity.this, responseFromHttpUrl);

                return movies;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Movie[] movies) {
            super.onPostExecute(movies);
            adapter = new RecyclerViewAdapter(movies);
            mRecyclerView.setAdapter(adapter);
        }
    }

}
