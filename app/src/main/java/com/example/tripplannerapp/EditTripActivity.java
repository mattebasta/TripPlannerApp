package com.example.tripplannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class EditTripActivity extends AppCompatActivity {
    private DatePickerDialog editSDatePickerDialog;
    private DatePickerDialog editEDatePickerDialog;
    public static final String EXTRA_TRIP_NAME = "com.example.tripplannerapp.EXTRA_TRIP_NAME";
    public static final String EXTRA_TRIP_DESC = "com.example.tripplannerapp.EXTRA_TRIP_DESC";
    public static final String EXTRA_TRIP_SDATE = "com.example.tripplannerapp.EXTRA_TRIP_SDATE";
    public static final String EXTRA_TRIP_EDATE = "com.example.tripplannerapp.EXTRA_TRIP_EDATE";
    private Button saveChangeBtn;
    private Button editSDateBtn;
    private Button editEDateBtn;
    private TextView editTripNameTV;
    private EditText editTripDescET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_trip);

        initDatePicker();
        editTripNameTV = findViewById(R.id.editTripNameTV);
        editTripDescET = findViewById(R.id.editTripDescET);
        editSDateBtn = findViewById(R.id.editTripSDateBtn);
        editEDateBtn = findViewById(R.id.editTripEDateBtn);
        saveChangeBtn = findViewById(R.id.SaveChangeBtn);

        Intent intent = getIntent();
        editTripNameTV.setText(intent.getStringExtra(EXTRA_TRIP_NAME));
        editTripDescET.setText(intent.getStringExtra(EXTRA_TRIP_DESC));
        editSDateBtn.setText(intent.getStringExtra(EXTRA_TRIP_SDATE));
        editEDateBtn.setText(intent.getStringExtra(EXTRA_TRIP_EDATE));

        setTitle("Edit Trip Details");

        saveChangeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeIntent = new Intent();
                changeIntent.putExtra("updateTripName", editTripNameTV.getText().toString());
                changeIntent.putExtra("updateTripDesc", editTripDescET.getText().toString());
                changeIntent.putExtra("updateTripSDate", editSDateBtn.getText().toString());
                changeIntent.putExtra("updateTripEDate", editEDateBtn.getText().toString());
                setResult(42, changeIntent);
                EditTripActivity.super.onBackPressed();
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String startDate = makeDateString(dayOfMonth, month, year);
                editSDateBtn.setText(startDate);
            }
        };

        DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String endDate = makeDateString(dayOfMonth, month, year);
                editEDateBtn.setText(endDate);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        editSDatePickerDialog = new DatePickerDialog(this, style, startDateSetListener, year, month, day);
        editSDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        editEDatePickerDialog = new DatePickerDialog(this, style, endDateSetListener, year ,month, day);
        editEDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
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
        editSDatePickerDialog.show();
    }
    public void endOpenDatePicker(View view){
        editEDatePickerDialog.show();
    }
}