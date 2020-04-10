package com.exmaple.android.popularmovies;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.exmaple.android.popularmovies.adapter.RecyclerViewAdapter;
import com.exmaple.android.popularmovies.data.Movie;
import com.exmaple.android.popularmovies.utils.MovieDataJsonUtils;
import com.exmaple.android.popularmovies.utils.NetworkUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    //TODO remove this api key when you publish your app

    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_recyclerView);
        gridLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mImageView = findViewById(R.id.iv_image);

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
        URL url = null;
        try {
            url = NetworkUtils.sortMovies(item.toString());
        } catch (MalformedURLException e) {
        }
        new MovieDbQueryTask().execute(url);
        return true;
    }

    private class MovieDbQueryTask extends AsyncTask<URL, Void, Movie[]> {

        @Override
        protected Movie[] doInBackground(URL... urls) {
            Log.d("MainActivity", "doInBackground: ");
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
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(movies);
            mRecyclerView.setAdapter(adapter);
        }
    }

}
