package com.example.tripplannerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Button addNewTripBtn;
    private RecyclerView recyclerView;
    private ArrayList<Trip> tripList;
    private tripRecyclerAdapter.OnClickListener listener;


    ActivityResultLauncher<Intent> AddNewTripActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 78) {
                        Intent intent = result.getData();
                        if (intent != null){
                            String name = intent.getStringExtra("TripName");
                            String desc = intent.getStringExtra("TripDesc");
                            String sDate = intent.getStringExtra("StartDate");
                            String eDate = intent.getStringExtra("EndDate");
                            tripList.add(new Trip(name, desc, sDate, eDate));
                            setAdapter();
                        }
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.tripRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        new ItemTouchHelper(mainActivityTouchHelperCallback).attachToRecyclerView(recyclerView);
        tripList = new ArrayList<>();
        setAdapter();


        addNewTripBtn = (Button) findViewById(R.id.newTrip);

        addNewTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewTripActivity();
            }

            private void openAddNewTripActivity() {
                Intent intent = new Intent(MainActivity.this, AddNewTripActivity.class);
                AddNewTripActivityResultLauncher.launch(intent);
                //startActivity(intent);
            }
        });

    }

    private void setAdapter() {
        setOnClickListner();
        tripRecyclerAdapter adapter = new tripRecyclerAdapter(tripList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setOnClickListner() {
        listener = new tripRecyclerAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), tripDetailsActivity.class);
                intent.putExtra("theTripName", tripList.get(position).getTripName());
                intent.putExtra("theTripDesc", tripList.get(position).getTripDesc());
                intent.putExtra("theTripSDate", tripList.get(position).getStartDate());
                intent.putExtra("theTripEDate", tripList.get(position).getEndDate());
                startActivity(intent);
            }
        };
    }

    ItemTouchHelper.SimpleCallback mainActivityTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            tripList.remove(viewHolder.getAdapterPosition());
            setAdapter();
        }
    };


}