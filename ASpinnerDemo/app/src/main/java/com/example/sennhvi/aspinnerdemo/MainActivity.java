package com.example.sennhvi.aspinnerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;

    private List<String> dataList;

    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = (Spinner) findViewById(R.id.spinner);

        dataList = new ArrayList<>(); // 1.init list
        dataList.add("NYC"); // 2. add element
        dataList.add("OKC");
        dataList.add("HOU");
        dataList.add("LA");

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dataList); // 3. init adapter with data,set layout resource
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // 4. set dropdown view

        spinner.setAdapter(arrayAdapter); // 5. connect spinner with adapter
    }
}
