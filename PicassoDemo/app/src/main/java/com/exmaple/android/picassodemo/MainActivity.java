package com.exmaple.android.picassodemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        String url = "http://i.imgur.com/DvpvklR.png";

        Picasso.get()
                .load(url)
                .fit()
                .into(imageView);

        Picasso.get()
                .load(url)
                .fit()
                .error(R.drawable.ic_launcher_background);
    }
}
