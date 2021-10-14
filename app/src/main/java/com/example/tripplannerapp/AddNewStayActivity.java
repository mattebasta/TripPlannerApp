package com.example.tripplannerapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Calendar;

//Todo: add alert to notify the completion of all field when adding a new element to a recycler view
public class AddNewStayActivity extends AppCompatActivity {
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private Button stayFromBtn;
    private Button stayToBtn;
    private Button saveNewStayBtn;
    private Button selectStayLocationBtn;
    EditText editTextstayPlace;
    int PLACE_PICKER_REQUEST = 1;
    TextView latAndLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_stay);

        initDatePicker();
        editTextstayPlace = findViewById(R.id.editTextStayPlace);
        stayFromBtn = findViewById(R.id.stayFromButton);
        stayToBtn = findViewById(R.id.stayToButton);
        saveNewStayBtn = findViewById(R.id.saveNewStayButton);
        stayFromBtn.setText(getTodayDate());
        stayToBtn.setText(getTodayDate());
        latAndLong = findViewById(R.id.textView14);
        selectStayLocationBtn = findViewById(R.id.setStayLocationBtn);

        selectStayLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }


        });



        saveNewStayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewStayIntent = new Intent();
                addNewStayIntent.putExtra("StayPlace", editTextstayPlace.getText().toString());
                addNewStayIntent.putExtra("StayFrom", stayFromBtn.getText().toString());
                addNewStayIntent.putExtra("StayTo", stayToBtn.getText().toString());
                addNewStayIntent.putExtra("StayLatAndLong",latAndLong.getText());
                setResult(80, addNewStayIntent);
                AddNewStayActivity.super.onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {

            }
        }
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
        DatePickerDialog.OnDateSetListener fromDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String fromStayDate = makeDateString(dayOfMonth, month, year);
                stayFromBtn.setText(fromStayDate);
            }
        };

        DatePickerDialog.OnDateSetListener toDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String toStayDate = makeDateString(dayOfMonth, month, year);
                stayToBtn.setText(toStayDate);
            }
        };



        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        fromDatePickerDialog = new DatePickerDialog(this, style, fromDateSetListener, year, month, day);
        toDatePickerDialog = new DatePickerDialog(this, style, toDateSetListener, year, month, day);

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

    public void fromStayOpenDatePicker(View view){ fromDatePickerDialog.show(); }
    public void toStayOpenDatePicker(View view){ toDatePickerDialog.show(); }

}