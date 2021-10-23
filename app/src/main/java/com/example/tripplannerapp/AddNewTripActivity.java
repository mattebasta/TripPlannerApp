package com.example.tripplannerapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddNewTripActivity extends AppCompatActivity {
    private DatePickerDialog startDatePickerDialog;
    private DatePickerDialog endDatePickerDialog;
    private Button startDateButton;
    private Button endDateButton;
    private Button saveTrip;
    EditText TripName;
    EditText TripDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_trip);

        initDatePicker();
        TripDesc = findViewById(R.id.editTextTripDesc);
        TripName = findViewById(R.id.editTextTripName);
        startDateButton = findViewById(R.id.button2);
        endDateButton = findViewById(R.id.button3);
        startDateButton.setText(getTodayDate());
        endDateButton.setText(getTodayDate());

        saveTrip = findViewById(R.id.saveTripButton);
        saveTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aNewTripIntent = new Intent();
                aNewTripIntent.putExtra("TripName", TripName.getText().toString());
                aNewTripIntent.putExtra("TripDesc", TripDesc.getText().toString());
                aNewTripIntent.putExtra("StartDate", startDateButton.getText().toString());
                aNewTripIntent.putExtra("EndDate", endDateButton.getText().toString());
                if(TripName.getText().toString().isEmpty() || TripDesc.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Complete all field",Toast.LENGTH_SHORT).show();
                }
                else{
                    setResult(78, aNewTripIntent);
                    AddNewTripActivity.super.onBackPressed();
                }
            }
        });
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String startDate = makeDateString(dayOfMonth, month, year);
                startDateButton.setText(startDate);
            }

        };

        DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String endDate = makeDateString(dayOfMonth, month, year);
                endDateButton.setText(endDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        startDatePickerDialog = new DatePickerDialog(this, style, startDateSetListener, year, month, day);
        startDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        endDatePickerDialog = new DatePickerDialog(this, style, endDateSetListener, year ,month, day);
        endDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
    }

    private String makeDateString(int day, int month, int year){
        return  getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month){
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return  "JAN";
    }

    public void startOpenDatePicker(View view){
        startDatePickerDialog.show();
    }
    public void endOpenDatePicker(View view){
        endDatePickerDialog.show();
    }
}