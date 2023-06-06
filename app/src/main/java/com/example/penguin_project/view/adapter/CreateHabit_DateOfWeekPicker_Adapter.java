package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.TimeOFWeek;

import java.util.ArrayList;

public class CreateHabit_DateOfWeekPicker_Adapter extends RecyclerView.Adapter<CreateHabit_DateOfWeekPicker_Adapter.DoWDateViewHolder> {

    ArrayList<TimeOFWeek> timeOFWeeks;

    Context context;

    private int selectedItemPosition = RecyclerView.NO_POSITION;


    public CreateHabit_DateOfWeekPicker_Adapter(ArrayList<TimeOFWeek> arrDate, Context context, int selectedItemPosition){

        this.timeOFWeeks = arrDate;
        this.context = context;
        this.selectedItemPosition = selectedItemPosition;
    }

    @NonNull
    @Override
    public DoWDateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rcv_item, parent, false);
        return new DoWDateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoWDateViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DoWDateViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_TimeOfWeek;
        public DoWDateViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_TimeOfWeek = itemView.findViewById(R.id.tvTimeOfWeek);
        }
    }
}
