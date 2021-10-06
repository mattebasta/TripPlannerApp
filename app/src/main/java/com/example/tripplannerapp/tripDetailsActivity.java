package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class tripDetailsActivity extends AppCompatActivity {

    private TextView theTripName;
    private TextView theTripDesc;
    private TextView theTripSDate;
    private TextView theTripEDate;
    private Button addNewStageBtn;
    private RecyclerView stayRecyclerView;
    private RecyclerView shiftRecyclerView;
    ArrayList<Stay> stayArrayList;
    ArrayList<Shift> shiftArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        stayRecyclerView = findViewById(R.id.stayRecyclerView);
        stayRecyclerView.addItemDecoration(new DividerItemDecoration(stayRecyclerView.getContext(),DividerItemDecoration.VERTICAL));
        shiftRecyclerView = findViewById(R.id.shiftRecyclerView);
        shiftRecyclerView.addItemDecoration(new DividerItemDecoration(shiftRecyclerView.getContext(),  DividerItemDecoration.VERTICAL));
        stayArrayList = new ArrayList<>();
        shiftArrayList = new ArrayList<>();


        theTripName = findViewById(R.id.theTripName);
        theTripDesc = findViewById(R.id.theTripDesc);
        theTripSDate = findViewById(R.id.theTripStart);
        theTripEDate = findViewById(R.id.theTripEnd);
//        addNewStageBtn = findViewById(R.id.addNewStage);

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