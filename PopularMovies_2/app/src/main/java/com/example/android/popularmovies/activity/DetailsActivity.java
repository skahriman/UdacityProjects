package com.example.android.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.adapter.TrailerAdapter;
import com.example.android.popularmovies.data.Movie;
import com.example.android.popularmovies.database.AppDatabase;
import com.example.android.popularmovies.utils.AppExecutors;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";
    private boolean isAdded;

    @BindView(R.id.tv_title) TextView mTitle;
    @BindView(R.id.iv_image_detail) ImageView mImage;
    @BindView(R.id.tv_releaseDate) TextView mReleaseDate;
    @BindView(R.id.tv_voteAverage) TextView mVoteAverage;
    @BindView(R.id.tv_overView) TextView mOverview;

    @BindView(R.id.rv_trailerRecyclerView) RecyclerView trailerRecyclerView;

    private TrailerAdapter mTrailerAdapter;

    private Movie movie;

    public DetailsActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        String id = intent.getStringExtra(getApplicationContext().getString(R.string.id));
        String title = intent.getStringExtra(getApplicationContext().getString(R.string.title));
        String release_date = intent.getStringExtra(getApplicationContext().getString(R.string.release_date));
        String backdrop_path = intent.getStringExtra(getApplicationContext().getString(R.string.backdrop_path));
        String poster_path = intent.getStringExtra(getApplicationContext().getString(R.string.poster_path));
        String vote_average = intent.getStringExtra(getApplicationContext().getString(R.string.vote_average));
        String overview = intent.getStringExtra(getApplicationContext().getString(R.string.over_view));
        String[] trailerUrls = intent.getStringArrayExtra(getString(R.string.listOfTrailers));

        movie = new Movie(id, title, release_date, backdrop_path, poster_path, vote_average, overview, trailerUrls);

        mTitle.setText(title);
        String image_path = this.getApplicationContext().getString(R.string.image_path)
                + intent.getStringExtra(getString(R.string.poster_path));
        Picasso.get().load(image_path).into(mImage);
        mReleaseDate.setText(release_date);
        mOverview.setText(overview);
        String delimiter = getString(R.string.out_of_ten);
        String average = vote_average + delimiter;
        mVoteAverage.setText(average);

        mTrailerAdapter = new TrailerAdapter(trailerUrls);
        trailerRecyclerView.setAdapter(mTrailerAdapter);
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.detail, menu);
        if(isMovieAdded(menu))
            menu.getItem(0).setIcon(R.drawable.star_icon);
        else
            menu.getItem(0).setIcon(R.drawable.star_icon_default);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        updateFavoriteStatus(item);
        return true;
    }

    /**
     * Update the status of favorite icon
     * if the movie is in DB remove it when clicked to star button
     * if the movie is not in DB add it to DB
     * @param item
     */
    private void updateFavoriteStatus(MenuItem item) {
        if(isAdded) {
            removeFromFavorites();
            item.setIcon(R.drawable.star_icon_default);
            isAdded = false;
        }
        else {
            addToFavorites();
            item.setIcon(R.drawable.star_icon);
            isAdded = true;
        }
    }

    /**
     * Add the movie to DB
     */
    private void addToFavorites() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(DetailsActivity.this).getMovieDao().addMovie(DetailsActivity.this.movie);
            }
        });

    }

    /**
     * Remove the movie from DB
     */
    private void removeFromFavorites() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(DetailsActivity.this).getMovieDao().deleteMovie(DetailsActivity.this.movie);
            }
        });
    }

    /**
     * Check if the movie is already in database for favorite movies
     * @param menu is the Menu
     * @return boolean
     */
    private boolean isMovieAdded(final Menu menu) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Movie movie = AppDatabase.getInstance(DetailsActivity.this).getMovieDao().getMovie(DetailsActivity.this.movie.getId());
                if(movie != null) {
                    isAdded = true;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            menu.getItem(0).setIcon(R.drawable.star_icon);
                        }
                    });
                }
                else
                    isAdded = false;
            }
        });
        return isAdded;
    }
}
