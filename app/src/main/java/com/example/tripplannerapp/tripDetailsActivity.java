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

import android.app.ActionBar;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Todo: Add Map and get all the long and lat of stay and shift
//Todo: fix activity details UI
//Todo: implement data persistance through sqlLite and rooms
public class tripDetailsActivity extends AppCompatActivity {

    private TextView theTripName;
    private TextView theTripDesc;
    private TextView theTripSDate;
    private TextView theTripEDate;
    private Button addNewStayBtn;
    private Button addNewShiftBtn;
    private RecyclerView stayRecyclerView;
    private RecyclerView shiftRecyclerView;
    private ImageButton toMapBtn;
    ArrayList<Stay> stayArrayList;
    ArrayList<Shift> shiftArrayList;

    ActivityResultLauncher<Intent> GetNewShiftResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 79) {
                        Intent intent = result.getData();
                        if (intent != null){
                            String departurePlace = intent.getStringExtra("Departure");
                            String arrivePlace = intent.getStringExtra("Arrive");
                            String dateOfShift = intent.getStringExtra("DateOfShift");
                            shiftArrayList.add(new Shift(departurePlace,arrivePlace,dateOfShift));
                            setShiftAdapter();
                        }
                    }
                }
            });

    ActivityResultLauncher<Intent> GetNewStayResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == 80) {
                        Intent intent = result.getData();
                        if (intent != null){
                            String stayPlace = intent.getStringExtra("StayPlace");
                            String fromStayDate = intent.getStringExtra("StayFrom");
                            String toStayDate = intent.getStringExtra("StayTo");
                            String stayLatLng = intent.getStringExtra("StayLatAndLong");


                            stayArrayList.add(new Stay(stayPlace, fromStayDate, toStayDate));
                            setStayAdapter();
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        stayRecyclerView = findViewById(R.id.stayRecyclerView);
        stayRecyclerView.addItemDecoration(new DividerItemDecoration(stayRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        shiftRecyclerView = findViewById(R.id.shiftRecyclerView);
        shiftRecyclerView.addItemDecoration(new DividerItemDecoration(shiftRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        stayArrayList = new ArrayList<>();
        shiftArrayList = new ArrayList<>();

        new ItemTouchHelper(stayTouchHelperCallback).attachToRecyclerView(stayRecyclerView);
        new ItemTouchHelper(shiftTouchHelperCallback).attachToRecyclerView(shiftRecyclerView);


        theTripName = findViewById(R.id.theTripName);
        theTripDesc = findViewById(R.id.theTripDesc);
        theTripSDate = findViewById(R.id.theTripStart);
        theTripEDate = findViewById(R.id.theTripEnd);

        stayArrayList.add(new Stay("Roma","qaaa","aaaa"));
        setStayAdapter();
        shiftArrayList.add(new Shift("Roma","qaaa","aaaa"));
        setShiftAdapter();


        Bundle content = getIntent().getExtras();
        if (content != null){
            theTripName.setText(content.getString("theTripName"));
            theTripDesc.setText(content.getString("theTripDesc"));
            theTripSDate.setText(content.getString("theTripSDate"));
            theTripEDate.setText(content.getString("theTripEDate"));
        }

        addNewShiftBtn = findViewById(R.id.addNewShift);
        addNewShiftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewShiftActivity();
            }

            private void openAddNewShiftActivity() {
                Intent shiftIntent = new Intent(tripDetailsActivity.this, AddNewShiftActivity.class);
                GetNewShiftResultLauncher.launch(shiftIntent);
                //startActivity(intent);
            }
        });

        addNewStayBtn = findViewById(R.id.addNewStage);
        addNewStayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddNewStayActivity();
            }

            private void openAddNewStayActivity() {
                Intent stayIntent = new Intent(tripDetailsActivity.this, AddNewStayActivity.class);
                GetNewStayResultLauncher.launch(stayIntent);
//                startActivity(intent);
            }
        });

        toMapBtn = findViewById(R.id.toMapViewButton);
        toMapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openMapViewActivity(); }

            private void openMapViewActivity(){
                Intent mapIntent = new Intent(tripDetailsActivity.this, mapMapBoxActivity.class);
                startActivity(mapIntent);
            }
        });

    }

    private void setStayAdapter() {
        tripStayAdapter stayAdapter = new tripStayAdapter(stayArrayList);
        RecyclerView.LayoutManager stayLayoutManager = new LinearLayoutManager(getApplicationContext());
        stayRecyclerView.setLayoutManager(stayLayoutManager);
        stayRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stayRecyclerView.setAdapter(stayAdapter);
        stayAdapter.notifyDataSetChanged();
    }

    private void setShiftAdapter() {
        tripShiftAdapter shiftAdapter = new tripShiftAdapter(shiftArrayList);
        RecyclerView.LayoutManager shiftLayoutManager = new LinearLayoutManager(getApplicationContext());
        shiftRecyclerView.setLayoutManager(shiftLayoutManager);
        shiftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        shiftRecyclerView.setAdapter(shiftAdapter);
        shiftAdapter.notifyDataSetChanged();
    }


    ItemTouchHelper.SimpleCallback stayTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            stayArrayList.remove(viewHolder.getAdapterPosition());
            setStayAdapter();
        }
    };

    ItemTouchHelper.SimpleCallback shiftTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            shiftArrayList.remove(viewHolder.getAdapterPosition());
            setShiftAdapter();
        }
    };

}