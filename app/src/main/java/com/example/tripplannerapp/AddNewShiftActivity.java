package com.example.tripplannerapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;

import java.util.Calendar;

//Todo: add alert to notify the completion of all field when adding a new element to a recycler view
public class AddNewShiftActivity extends AppCompatActivity {
    private DatePickerDialog dateOfShiftPickerDialog;
    EditText editTextDeparture;
    EditText editTextArrive;
    private Button shiftDateBtn;
    private Button newShiftBtn;
    int REQUEST_ARRIVE_AUTO_COMPLETE = 2; //arrive
    int REQUEST_DEPARTURE_AUTO_COMPLETE = 3; //departure

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_shift);

        initDatePicker();
        editTextArrive = findViewById(R.id.editTextArrive);
        editTextDeparture = findViewById(R.id.editTextDeparture);
        shiftDateBtn = findViewById(R.id.dateOfShift);
        shiftDateBtn.setText(getTodayDate());
        newShiftBtn = findViewById(R.id.saveNewShiftButton);

        editTextDeparture.setInputType(InputType.TYPE_NULL);
        editTextArrive.setInputType(InputType.TYPE_NULL);

        editTextDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent depSelectionIntent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(getString(R.string.access_token))
                        .placeOptions(PlaceOptions.builder().backgroundColor(Color.parseColor("#ffffff")).build()).build(AddNewShiftActivity.this);
                startActivityForResult(depSelectionIntent, REQUEST_DEPARTURE_AUTO_COMPLETE);
            }
        });


        editTextArrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arrSelectionIntent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(getString(R.string.access_token))
                        .placeOptions(PlaceOptions.builder().build()).build(AddNewShiftActivity.this);
                startActivityForResult(arrSelectionIntent, REQUEST_ARRIVE_AUTO_COMPLETE);
            }
        });

        newShiftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewShiftIntent = new Intent();
                addNewShiftIntent.putExtra("Departure",editTextDeparture.getText().toString());
                addNewShiftIntent.putExtra("Arrive",editTextArrive.getText().toString());
                addNewShiftIntent.putExtra("DateOfShift",shiftDateBtn.getText().toString());
                setResult(79,addNewShiftIntent);
                AddNewShiftActivity.super.onBackPressed();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_DEPARTURE_AUTO_COMPLETE){
            CarmenFeature depFeature = PlaceAutocomplete.getPlace(data);
            Toast.makeText(this, depFeature.text(),Toast.LENGTH_LONG).show();
            editTextDeparture.setText(depFeature.text());
        }
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_ARRIVE_AUTO_COMPLETE){
            CarmenFeature arrFeature = PlaceAutocomplete.getPlace(data);
            Toast.makeText(this, arrFeature.text(), Toast.LENGTH_LONG).show();
            editTextArrive.setText(arrFeature.text());
        }
    }

    private String getTodayDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateOfShiftListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String dateOfShift = makeDateString(dayOfMonth, month, year);
                shiftDateBtn.setText(dateOfShift);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;

        dateOfShiftPickerDialog = new DatePickerDialog(this, style, dateOfShiftListener, year, month, day);
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

    public void  dateOfShiftOpenDatePicker (View view){
        dateOfShiftPickerDialog.show();
    }
}