package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class tripRecyclerAdapter extends RecyclerView.Adapter<tripRecyclerAdapter.TripHolder> {
    private ArrayList<Trip> tripList;
    private OnClickListener listener;

    public tripRecyclerAdapter(ArrayList<Trip> tripList, OnClickListener listener) {
        this.tripList = tripList;
        this.listener = listener;
    }


    public class TripHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView Name;
        private TextView Desc;
        private TextView Start;
        private TextView End;


        public TripHolder(final View view){
            super(view);
            Name = view.findViewById(R.id.tripName);
            Desc = view.findViewById(R.id.tripDesc);
            Start = view.findViewById(R.id.startDate);
            End = view.findViewById(R.id.endDate);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(v, getAdapterPosition());
        }
    }


    @NonNull
    @Override
    public tripRecyclerAdapter.TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTripView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item_list, parent, false);
        return new TripHolder(itemTripView);
    }

    @Override
    public void onBindViewHolder(@NonNull tripRecyclerAdapter.TripHolder holder, int position) {
        String name = tripList.get(position).getTripName();
        String desc = tripList.get(position).getTripDesc();
        String start = tripList.get(position).getStartDate();
        String end = tripList.get(position).getEndDate();
        holder.Name.setText(name);
        holder.Desc.setText(desc);
        holder.Start.setText(start);
        holder.End.setText(end);
    }
    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

}

