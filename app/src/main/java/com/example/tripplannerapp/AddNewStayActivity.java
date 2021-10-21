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

import com.mapbox.api.geocoding.v5.models.CarmenFeature;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.PlaceAutocomplete;
import com.mapbox.mapboxsdk.plugins.places.autocomplete.model.PlaceOptions;

import java.util.Calendar;

//Todo: add alert to notify the completion of all field when adding a new element to a recycler view
public class AddNewStayActivity extends AppCompatActivity {
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private Button stayFromBtn;
    private Button stayToBtn;
    private Button saveNewStayBtn;
    TextView StayLngTextView;
    TextView StayLatTextView;
    EditText editTextStayPlace;
    int REQUEST_STAY_AUTOCOMPLETE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_stay);

        initDatePicker();
        StayLngTextView = findViewById(R.id.StayLngTextView);
        StayLatTextView = findViewById(R.id.StayLatTextView);
        editTextStayPlace = findViewById(R.id.editTextStayPlace);
        stayFromBtn = findViewById(R.id.stayFromButton);
        stayToBtn = findViewById(R.id.stayToButton);
        saveNewStayBtn = findViewById(R.id.saveNewStayButton);
        stayFromBtn.setText(getTodayDate());
        stayToBtn.setText(getTodayDate());

        editTextStayPlace.setInputType(InputType.TYPE_NULL);
        editTextStayPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent placeSelectionIntent = new PlaceAutocomplete.IntentBuilder()
                        .accessToken(getString(R.string.access_token))
                        .placeOptions(PlaceOptions.builder().backgroundColor(Color.parseColor("#ffffff")).build()).build(AddNewStayActivity.this);
                startActivityForResult(placeSelectionIntent, REQUEST_STAY_AUTOCOMPLETE);
            }
        });


        saveNewStayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addNewStayIntent = new Intent();
                addNewStayIntent.putExtra("StayPlace", editTextStayPlace.getText().toString());
                addNewStayIntent.putExtra("StayFrom", stayFromBtn.getText().toString());
                addNewStayIntent.putExtra("StayTo", stayToBtn.getText().toString());
                addNewStayIntent.putExtra("StayLng", StayLngTextView.getText());
                addNewStayIntent.putExtra("StayLat", StayLatTextView.getText());
                setResult(80, addNewStayIntent);
                AddNewStayActivity.super.onBackPressed();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_STAY_AUTOCOMPLETE){
            CarmenFeature feature = PlaceAutocomplete.getPlace(data);
            double stayLng = feature.center().longitude();
            double stayLat = feature.center().latitude();

//            Log.d("TAG____", "onActivityResult: " + PlaceAutocomplete.getPlace(data).center() + "Lat: " + stayLat + "Lng: " + stayLng);
            editTextStayPlace.setText(feature.text());
            StayLngTextView.setText(Double.toString(stayLng));
            StayLatTextView.setText(Double.toString(stayLat));

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
        fromDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());
        toDatePickerDialog = new DatePickerDialog(this, style, toDateSetListener, year, month, day);
        toDatePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis());

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