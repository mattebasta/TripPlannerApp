package com.example.tripplannerapp;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.tripplannerapp.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    StayViewModel stayViewModel;
    ShiftViewModel shiftViewModel;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        shiftViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication(ShiftViewModel.class)));

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        stayViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(StayViewModel.class);
        stayViewModel.getStayCoordinates().observe(this, new Observer<List<StayDao.stayCoordinates>>() {
            @Override
            public void onChanged(List<StayDao.stayCoordinates> stayCoordinates) {
                for (int i = 0; i < stayViewModel.getStayCoordinates().getValue().size(); i++) {
                    LatLng indexMarker = new LatLng(stayCoordinates.get(i).StayLatitude, stayCoordinates.get(i).StayLongitude);
                    mMap.addMarker(new MarkerOptions().position(indexMarker)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                }
            }
        });

        shiftViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(ShiftViewModel.class);
        shiftViewModel.getShiftDepCoords().observe(this, new Observer<List<ShiftDao.shiftDepCoordinates>>() {
            @Override
            public void onChanged(List<ShiftDao.shiftDepCoordinates> shiftDepCoordinates) {
                for (int sd = 0; sd < shiftViewModel.getShiftDepCoords().getValue().size(); sd++) {
                    LatLng indexMarker = new LatLng(shiftDepCoordinates.get(sd).LatitudeDep, shiftDepCoordinates.get(sd).LongitudeDep);
                    mMap.addMarker(new MarkerOptions().position(indexMarker)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                }
            }
        });

        shiftViewModel.getShiftArrCoords().observe(this, new Observer<List<ShiftDao.shiftArrCoordinates>>() {
            @Override
            public void onChanged(List<ShiftDao.shiftArrCoordinates> shiftArrCoordinates) {
                for (int sd = 0; sd < shiftViewModel.getShiftArrCoords().getValue().size(); sd++) {
                    LatLng indexMarker = new LatLng(shiftArrCoordinates.get(sd).LatitudeArr, shiftArrCoordinates.get(sd).LongitudeArr);
                    mMap.addMarker(new MarkerOptions().position(indexMarker)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                }
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng rome = new LatLng(41.890373, 12.490733);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rome));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(5));
    }
}