package com.example.penguin_project.view.adapter;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.fragment.MenuFragment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private List<Habit_Day> habitDates;
    public CalendarAdapter(List<Habit_Day> days) {
        this.habitDates = days;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Lay data ve store item de implement special item
        HabitDataBase habitDataBase = HabitDataBase.getInstance(holder.itemView.getContext());
        List<StoreItem> storeItemList = habitDataBase.habitDAO().getStoreItemByTypeNotLiveData("special item");
        StoreItem freeze = storeItemList.get(0);
        StoreItem protection = storeItemList.get(1);
        StoreItem repair = storeItemList.get(2);
        StoreItem freeTicket = storeItemList.get(3);

        //
        Habit_Day habit_day = habitDates.get(position);
        if(habit_day != null){
            LocalDate day = habit_day.getHabit_Day_id();
            holder.dayTextView.setText(String.valueOf(day.getDayOfMonth()));
            if(habit_day.getIsDone()){
                holder.dayTextView.setBackgroundResource(R.drawable.item_daystreakmap_shape_done);
            }
            if(habit_day.getHabit_Day_id().isBefore(LocalDate.now())){
                if(!habit_day.getIsDone()){
                    if (habit_day.getHabit_Day_id().equals(LocalDate.of(2023, 7, 10)) && protection.getIsPurchased()) {
                        holder.dayTextView.setBackgroundResource(R.drawable.item_daystreakmap_shape_protection);
                        holder.dayTextView.setText("");
                    }
                    else if (habit_day.getHabit_Day_id().equals(LocalDate.of(2023, 7, 15)) && freeze.getIsPurchased()) {
                        holder.dayTextView.setBackgroundResource(R.drawable.item_daystreakmap_shape_freeze);
                        holder.dayTextView.setText("");
                    }
                    else {
                        holder.dayTextView.setBackgroundResource(R.drawable.item_daystreakmap_shape_failed);
                    }
                }

            }
            else {
                holder.dayTextView.setBackgroundResource(R.drawable.item_daystreakmap_shape_defaut);
            }
        }
        else {
            holder.dayTextView.setText("");
            if(ThemeControl.getInstance(holder.itemView.getContext()).getData("Mode", -1) == 1){
                holder.dayTextView.setBackgroundResource(R.color.mainBackgroundDark);
            }
            else {
                holder.dayTextView.setBackgroundResource(R.color.mainBackgroundLight);
            }

        }
    }

    @Override
    public int getItemCount() {
        return habitDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }
    }
}