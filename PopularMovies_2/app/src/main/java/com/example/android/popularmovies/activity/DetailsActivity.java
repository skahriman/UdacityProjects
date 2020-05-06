package com.example.android.popularmovies.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {
    private static final String TAG = "DetailsActivity";

    @BindView(R.id.tv_title) TextView title;
    @BindView(R.id.iv_image_detail) ImageView image;
    @BindView(R.id.tv_releaseDate) TextView releaseDate;
    @BindView(R.id.tv_voteAverage) TextView voteAverage;
    @BindView(R.id.tv_overView) TextView overView;

    public DetailsActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        Intent intent = getIntent();

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
