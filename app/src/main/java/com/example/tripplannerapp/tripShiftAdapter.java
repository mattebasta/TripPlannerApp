package com.example.tripplannerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class tripShiftAdapter extends RecyclerView.Adapter<tripShiftAdapter.shiftHolder> {
    private List<Shift> shiftList = new ArrayList<>();
    private OnItemClickListener shiftListener;


    public class shiftHolder extends RecyclerView.ViewHolder{
        private TextView Departure;
        private TextView Arrive;
        private TextView Date;


        public shiftHolder(final View view) {
            super(view);
            Departure = view.findViewById(R.id.shiftDep);
            Arrive = view.findViewById(R.id.shiftArr);
            Date = view.findViewById(R.id.shiftDate);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(shiftListener != null && position != RecyclerView.NO_POSITION){
                        shiftListener.onItemClick(shiftList.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public shiftHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemShiftView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_shift_item_list, parent, false);
        return new shiftHolder(itemShiftView);
    }
    @Override
    public void onBindViewHolder(@NonNull tripShiftAdapter.shiftHolder shiftHolder, int position) {
        Shift currentShift = shiftList.get(position);
        shiftHolder.Departure.setText(currentShift.getDeparture());
        shiftHolder.Arrive.setText(currentShift.getArrive());
        shiftHolder.Date.setText(currentShift.getDateOfShift());
    }

    public interface OnItemClickListener {
        void onItemClick(Shift shift);
    }

    public void setOnItemClickListener(OnItemClickListener shiftListener) {
        this.shiftListener = shiftListener;
    }

    @Override
    public int getItemCount() {
        return shiftList.size();
    }

    public void setShiftList(List<Shift> shiftList){
        this.shiftList = shiftList;
        notifyDataSetChanged();
    }

    public Shift getShiftAt(int position){
        return shiftList.get(position);
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }
}
