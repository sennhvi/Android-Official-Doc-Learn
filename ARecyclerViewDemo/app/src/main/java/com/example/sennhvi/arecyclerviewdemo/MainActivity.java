package com.example.sennhvi.arecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * app -> Open module settings -> dependencies -> add Library dependencies -> RecyclerView.v7
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView; // 1. declare RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = new RecyclerView(this); // 2. create RecyclerView
        setContentView(recyclerView);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // 3. set RecyclerView Layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3)); // 3. set RecyclerView Layout, 3 columns
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)); // 3. set RecyclerView Layout, revert false, horizontal layout

        recyclerView.setAdapter(new MyAdapter());
    }

}
