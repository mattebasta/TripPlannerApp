package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addNewTripBtn;
    private RecyclerView recyclerView;
    private ArrayList<Trip> tripList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.tripRecyclerView);
        tripList = new ArrayList<>();

        setTripInfo();
        setAdapter();

        addNewTripBtn = (Button) findViewById(R.id.newTrip);

        addNewTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddNewTripActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setTripInfo(){
        tripList.add(new Trip("Vacanza","Vacanza alle Maldive","11/11/11","19/11/11"));
    }

    private void setAdapter() {
        tripRecyclerAdapter adapter = new tripRecyclerAdapter(tripList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}