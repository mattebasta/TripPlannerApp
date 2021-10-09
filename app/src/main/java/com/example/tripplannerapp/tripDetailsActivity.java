package com.example.tripplannerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

//Todo: Add Map and get all the long and lat to stay and shift
//Todo: fix activity details UI
//Todo: add a map view to get the lat and long
//Todo: add a button to UI to get to the map after getting the details
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

    }

    private void setStayAdapter() {
        tripStayAdapter stayAdapter = new tripStayAdapter(stayArrayList);
        RecyclerView.LayoutManager stayLayoutManager = new LinearLayoutManager(getApplicationContext());
        stayRecyclerView.setLayoutManager(stayLayoutManager);
        stayRecyclerView.setItemAnimator(new DefaultItemAnimator());
        stayRecyclerView.setAdapter(stayAdapter);
    }

    private void setShiftAdapter() {
        tripShiftAdapter shiftAdapter = new tripShiftAdapter(shiftArrayList);
        RecyclerView.LayoutManager shiftLayoutManager = new LinearLayoutManager(getApplicationContext());
        shiftRecyclerView.setLayoutManager(shiftLayoutManager);
        shiftRecyclerView.setItemAnimator(new DefaultItemAnimator());
        shiftRecyclerView.setAdapter(shiftAdapter);
    }

}