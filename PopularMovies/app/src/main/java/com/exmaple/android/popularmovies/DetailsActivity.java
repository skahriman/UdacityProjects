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

    private TextView title;
    private ImageView image;
    private TextView releaseDate;
    private TextView voteAverage;
    private TextView overView;
    String image_path = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Log.d(TAG, "onCreate: ");
        Intent intent = getIntent();

        title = findViewById(R.id.tv_title);
        image = findViewById(R.id.iv_image_detail);
        releaseDate = findViewById(R.id.tv_releaseDate);
        voteAverage = findViewById(R.id.tv_voteAverage);
        overView = findViewById(R.id.tv_overView);

        title.setText(intent.getStringExtra("title"));
        image_path = image_path + intent.getStringExtra("poster_path");
        Picasso.get().load(image_path).into(image);
        releaseDate.setText(intent.getStringExtra("release_date"));
        voteAverage.setText(intent.getStringExtra("vote_average")+"/10");
        overView.setText(intent.getStringExtra("overView"));

    }
}
