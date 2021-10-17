package com.example.tripplannerapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;


//Todo: implement data persistance through rooms
public class tripDetailsActivity extends AppCompatActivity {
    private StayViewModel stayViewModel;
    private ShiftViewModel shiftViewModel;
    public static final String EXTRA_TRIP_NAME = "com.example.tripplannerapp.EXTRA_TRIP_NAME";
    public static final String EXTRA_TRIP_DESC = "com.example.tripplannerapp.EXTRA_TRIP_DESC";
    public static final String EXTRA_TRIP_SDATE = "com.example.tripplannerapp.EXTRA_TRIP_SDATE";
    public static final String EXTRA_TRIP_EDATE = "com.example.tripplannerapp.EXTRA_TRIP_EDATE";
    public static final String EXTRA_TRIP_ID = "com.example.tripplannerapp.EXTRA_TRIP_ID";

    private TextView theTripName;
    private TextView theTripDesc;
    private TextView theTripSDate;
    private TextView theTripEDate;
    private Button addNewStayBtn;
    private Button addNewShiftBtn;
    private RecyclerView stayRecyclerView;
    private RecyclerView shiftRecyclerView;
    private ImageButton toMapBtn;
    List<Stay> stayList;
    List<Shift> shiftList;

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
                            shiftList.add(new Shift(departurePlace,arrivePlace,dateOfShift));
                            shiftViewModel.insert(new Shift(departurePlace,arrivePlace,dateOfShift));
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
                            stayList.add(new Stay(stayPlace, fromStayDate, toStayDate));
                            stayViewModel.insert(new Stay(stayPlace,fromStayDate,toStayDate));
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //stay recyclerview management
        stayRecyclerView = findViewById(R.id.stayRecyclerView);
        stayRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        stayRecyclerView.setHasFixedSize(true);
        stayRecyclerView.addItemDecoration(new DividerItemDecoration(stayRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        stayList = new ArrayList<Stay>();

        final tripStayAdapter stayAdapter = new tripStayAdapter();
        stayRecyclerView.setAdapter(stayAdapter);

        stayViewModel = ViewModelProviders.of(this).get(StayViewModel.class);
        stayViewModel.getAllStay().observe(this, new Observer<List<Stay>>() {
            @Override
            public void onChanged(List<Stay> stayList) {
                stayAdapter.setStayList(stayList);
                stayAdapter.notifyDataSetChanged();
            }
        });
        //stay recyclerview item touch helper
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                stayViewModel.delete(stayAdapter.getStayAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(stayRecyclerView);

        //shift recycler view management
        shiftRecyclerView = findViewById(R.id.shiftRecyclerView);
        shiftRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        shiftRecyclerView.setHasFixedSize(true);
        shiftRecyclerView.addItemDecoration(new DividerItemDecoration(shiftRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        shiftList = new ArrayList<Shift>();

        final tripShiftAdapter shiftAdapter = new tripShiftAdapter();
        shiftRecyclerView.setAdapter(shiftAdapter);

        shiftViewModel = ViewModelProviders.of(this).get(ShiftViewModel.class);
        shiftViewModel.getAllShift().observe(this, new Observer<List<Shift>>() {
            @Override
            public void onChanged(List<Shift> shiftList) {
                shiftAdapter.setShiftList(shiftList);
                shiftAdapter.notifyDataSetChanged();
            }
        });
        //shift item touch helper
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                shiftViewModel.delete(shiftAdapter.getShiftAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(shiftRecyclerView);


        theTripName = findViewById(R.id.theTripName);
        theTripDesc = findViewById(R.id.theTripDesc);
        theTripSDate = findViewById(R.id.theTripStart);
        theTripEDate = findViewById(R.id.theTripEnd);



        Bundle content = getIntent().getExtras();
        if (content != null){
            theTripName.setText(content.getString(EXTRA_TRIP_NAME));
            theTripDesc.setText(content.getString(EXTRA_TRIP_DESC));
            theTripSDate.setText(content.getString(EXTRA_TRIP_SDATE));
            theTripEDate.setText(content.getString(EXTRA_TRIP_EDATE));
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

}