package com.exmaple.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    private String image_path = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d(TAG, "onCreate: ");
        Intent intent = getIntent();

        TextView title = findViewById(R.id.tv_title);
        ImageView image = findViewById(R.id.iv_image_detail);
        TextView releaseDate = findViewById(R.id.tv_releaseDate);
        TextView voteAverage = findViewById(R.id.tv_voteAverage);
        TextView overView = findViewById(R.id.tv_overView);

        title.setText(intent.getStringExtra(getString(R.string.title)));
        image_path = image_path + intent.getStringExtra(getString(R.string.poster_path));
        Picasso.get().load(image_path).into(image);
        releaseDate.setText(intent.getStringExtra(getString(R.string.release_date)));
        overView.setText(intent.getStringExtra(getString(R.string.over_view)));
        String vote_average = getString(R.string.vote_average);
        String delimiter = getString(R.string.delimiter);
        String average = intent.getStringExtra(vote_average) + delimiter;
        voteAverage.setText(average);
    }
}
