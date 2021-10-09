package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class tripStayAdapter extends RecyclerView.Adapter<tripStayAdapter.StayHolder> {
    private ArrayList<Stay> stayList;

    public tripStayAdapter(ArrayList<Stay> stayList){
        this.stayList = stayList;

    }

    public class StayHolder extends RecyclerView.ViewHolder {
        private TextView stayPlace;
        private TextView stayFrom;
        private TextView stayTo;


        public StayHolder(@NonNull View itemView) {
            super(itemView);
            stayPlace = itemView.findViewById(R.id.stayplace);
            stayFrom = itemView.findViewById(R.id.staysfrom);
            stayTo = itemView.findViewById(R.id.stayTo);

        }
    }


    @NonNull
    @Override
    public tripStayAdapter.StayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemStayView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_stay_item_list,parent,false);
        return new StayHolder(itemStayView);
    }

    @Override
    public void onBindViewHolder(@NonNull StayHolder holder, int position) {
        String stayPlace = stayList.get(position).getStayPlace();
        String stayFrom = stayList.get(position).getFromDate();
        String stayTo = stayList.get(position).getToDate();
        holder.stayPlace.setText(stayPlace);
        holder.stayFrom.setText(stayFrom);
        holder.stayTo.setText(stayTo);
    }

    @Override
    public int getItemCount() {
        return stayList.size();
    }

}
