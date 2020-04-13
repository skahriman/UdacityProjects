package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    public DetailsActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        TextView title = findViewById(R.id.tv_title);
        ImageView image = findViewById(R.id.iv_image_detail);
        TextView releaseDate = findViewById(R.id.tv_releaseDate);
        TextView voteAverage = findViewById(R.id.tv_voteAverage);
        TextView overView = findViewById(R.id.tv_overView);

        title.setText(intent.getStringExtra(getString(R.string.title)));
        String image_path = this.getApplicationContext().getString(R.string.image_path)
                + intent.getStringExtra(getString(R.string.backdrop_path));
        Picasso.get().load(image_path).into(image);
        releaseDate.setText(intent.getStringExtra(getString(R.string.release_date)));
        overView.setText(intent.getStringExtra(getString(R.string.over_view)));
        String vote_average = getString(R.string.vote_average);
        String delimiter = getString(R.string.out_of_ten);
        String average = intent.getStringExtra(vote_average) + delimiter;
        voteAverage.setText(average);
    }
}
