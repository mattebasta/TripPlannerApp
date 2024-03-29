package com.example.tripplannerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import java.util.List;



public class MainActivity extends AppCompatActivity{
    private TripViewModel tripViewModel;
    private Button addNewTripBtn;
    private RecyclerView recyclerView;
    private List<Trip> tripList;

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
                            tripViewModel.insert(new Trip(name,desc,sDate,eDate));
                        }
                    }
                }
            });

    ActivityResultLauncher<Intent> EditTripActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 42){
                        Intent intent = result.getData();
                        if (intent != null){
                            String updateName = intent.getStringExtra("updateTripName");
                            String updateDesc = intent.getStringExtra("updateTripDesc");
                            String updateSDate = intent.getStringExtra("updateTripSDate");
                            String updateEDate = intent.getStringExtra("updateTripEDate");
                            Log.d("TAG__________", "onActivityResult: " + updateName + " " + updateDesc + " " + updateSDate + " " + updateEDate);
                            tripViewModel.update(new Trip(updateName, updateDesc, updateSDate, updateEDate));
                        }
                    }
                }
            });

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.tripRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        tripList = new ArrayList<Trip>();

        final tripRecyclerAdapter adapter = new tripRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        tripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        tripViewModel.getAllTrip().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(List<Trip> tripList) {
                adapter.setTripList(tripList);
                adapter.notifyDataSetChanged();
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tripViewModel.delete(adapter.getTripAt(viewHolder.getAdapterPosition()));

            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new tripRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Trip trip) {
                Intent intent = new Intent(getApplicationContext(), tripDetailsActivity.class);
                intent.putExtra(tripDetailsActivity.EXTRA_TRIP_NAME, trip.getTripName());
                intent.putExtra(tripDetailsActivity.EXTRA_TRIP_DESC, trip.getTripDesc());
                intent.putExtra(tripDetailsActivity.EXTRA_TRIP_SDATE, trip.getStartDate());
                intent.putExtra(tripDetailsActivity.EXTRA_TRIP_EDATE, trip.getEndDate());
                startActivity(intent);
            }
        });

        adapter.setOnItemLongClickListener(new tripRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClickListener(Trip trip) {
                Intent intent = new Intent(MainActivity.this, EditTripActivity.class);
                intent.putExtra(EditTripActivity.EXTRA_TRIP_NAME, trip.getTripName());
                intent.putExtra(EditTripActivity.EXTRA_TRIP_DESC, trip.getTripDesc());
                intent.putExtra(EditTripActivity.EXTRA_TRIP_SDATE, trip.getStartDate());
                intent.putExtra(EditTripActivity.EXTRA_TRIP_EDATE, trip.getEndDate());
                EditTripActivityResultLauncher.launch(intent);
            }
        });

        addNewTripBtn = findViewById(R.id.newTrip);
        addNewTripBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewTripActivity();
            }

            private void openAddNewTripActivity() {
                Intent intent = new Intent(MainActivity.this, AddNewTripActivity.class);
                AddNewTripActivityResultLauncher.launch(intent);
            }
        });

    }
}