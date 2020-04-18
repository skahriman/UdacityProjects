package com.exmaple.android.recyclerviewwithimages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    String s1[], s2[];
    int imageResource[] = {R.drawable.cat, R.drawable.dog, R.drawable.cat, R.drawable.dog, R.drawable.cat, R.drawable.dog, R.drawable.cat, R.drawable.dog};

    MyRecyclerViewAdapter mMyRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_recyclerView);
        s1 = getResources().getStringArray(R.array.pet_name);
        s2 = getResources().getStringArray(R.array.description);

        mMyRecyclerViewAdapter = new MyRecyclerViewAdapter(this, s1, s2, imageResource);
        mRecyclerView.setAdapter(mMyRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
    }
}
