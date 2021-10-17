package com.example.tripplannerapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class tripRecyclerAdapter extends RecyclerView.Adapter<tripRecyclerAdapter.TripHolder> {
    private List<Trip> tripList = new ArrayList<>();
    private OnItemClickListener listener;


    public class TripHolder extends RecyclerView.ViewHolder{
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(tripList.get(position));
                    }

                }
            });
        }
    }


    @NonNull
    @Override
    public TripHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemTripView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_item_list, parent, false);
        return new TripHolder(itemTripView);
    }

    @Override
    public void onBindViewHolder(@NonNull tripRecyclerAdapter.TripHolder holder, int position) {
        Trip currentTrip = tripList.get(position);
        holder.Name.setText(currentTrip.getTripName());
        holder.Desc.setText(currentTrip.getTripDesc());
        holder.Start.setText(currentTrip.getStartDate());
        holder.End.setText(currentTrip.getEndDate());
    }

    public interface OnItemClickListener {
        void onItemClick(Trip trip);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return tripList.size();
    }

    public void setTripList(List<Trip> tripList){
        this.tripList = tripList;
        notifyDataSetChanged();
    }

    public Trip getTripAt(int position){
        return tripList.get(position);
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

}

