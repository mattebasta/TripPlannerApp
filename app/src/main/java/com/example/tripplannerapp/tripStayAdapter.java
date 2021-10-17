package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class tripStayAdapter extends RecyclerView.Adapter<tripStayAdapter.StayHolder> {
    private List<Stay> stayList = new ArrayList<>();
    private OnItemClickListener stayListener;


    public class StayHolder extends RecyclerView.ViewHolder {
        private TextView stayPlace;
        private TextView stayFrom;
        private TextView stayTo;


        public StayHolder(@NonNull View itemView) {
            super(itemView);
            stayPlace = itemView.findViewById(R.id.stayplace);
            stayFrom = itemView.findViewById(R.id.staysfrom);
            stayTo = itemView.findViewById(R.id.stayTo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (stayListener != null && position != RecyclerView.NO_POSITION){
                        stayListener.onItemClick(stayList.get(position));
                    }
                }
            });
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
        Stay currentStay = stayList.get(position);
        holder.stayPlace.setText(currentStay.getStayPlace());
        holder.stayFrom.setText(currentStay.getFromDate());
        holder.stayTo.setText(currentStay.getToDate());
    }

    public interface OnItemClickListener{
        void onItemClick(Stay stay);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.stayListener = listener;
    }

    @Override
    public int getItemCount() {
        return stayList.size();
    }

    public void setStayList(List<Stay> stayList){
        this.stayList = stayList;
        notifyDataSetChanged();
    }

    public Stay getStayAt(int position){
        return stayList.get(position);
    }

    public interface OnclickListener{
        void onClick(View v, int position);
    }
}
