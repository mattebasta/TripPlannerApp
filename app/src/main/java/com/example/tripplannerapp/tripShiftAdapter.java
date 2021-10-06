package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tripShiftAdapter extends RecyclerView.Adapter<tripShiftAdapter.shiftHolder> {
    private ArrayList<Shift> shiftList;

    tripShiftAdapter(ArrayList<Shift>shiftList){
        this.shiftList = shiftList;
    }

    public class shiftHolder extends RecyclerView.ViewHolder{
        private TextView Departure;
        private TextView Arrive;
        private TextView Date;


        public shiftHolder(@NonNull View itemView) {
            super(itemView);
            Departure = itemView.findViewById(R.id.shiftDep);
            Arrive = itemView.findViewById(R.id.shiftArr);
            Date = itemView.findViewById(R.id.shiftDate);
        }
    }

    @NonNull
    @Override
    public tripShiftAdapter.shiftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemShiftView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_shift_item_list, parent, false);
        return new shiftHolder(itemShiftView);
    }
    @Override
    public void onBindViewHolder(@NonNull tripShiftAdapter.shiftHolder shiftHolder, int position) {
        String Departure = shiftList.get(position).getDeparture();
        String Arrive = shiftList.get(position).getArrive();
        String Date = shiftList.get(position).getDateOfShift();
        shiftHolder.Departure.setText(Departure);
        shiftHolder.Arrive.setText(Arrive);
        shiftHolder.Date.setText(Date);
    }
    @Override
    public int getItemCount() {
        return shiftList.size();
    }


}
