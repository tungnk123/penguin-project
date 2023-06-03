package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class HabitItemAdapter extends RecyclerView.Adapter<HabitItemAdapter.ItemViewHolder> {

    private List<Habits> itemList;
    private Context context;
    private DayOfWeek dayOfWeek;

    public HabitItemAdapter(List<Habits> itemList, Context context, DayOfWeek dayOfWeek) {
        this.itemList = itemList;
        this.context = context;
        this.dayOfWeek = dayOfWeek;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.habit_item_layout, parent, false);
        return new ItemViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Habits itemName = itemList.get(position);
        List<Habit_DayOfWeek> habit_dayOfWeeks = new ArrayList<>();
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(itemName.getHabit_id(), dayOfWeek);
        if(habit_dayOfWeeks.size() > 0){
            Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
            holder.item_CurrentProgress.setText(habit_dayOfWeek.getProgress());
        }
        else {
            holder.item_CurrentProgress.setText("0");
        }
        holder.itemColor.setBackgroundResource(itemName.getColor());
        holder.item_Title.setText(itemName.getTitle());
        holder.itemIcon.setImageResource(itemName.getIcon());
        holder.item_timePerDay.setText(String.valueOf(itemName.getTimePerDay()));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void removeItem(int position) {
        Habits habits = itemList.get(position);
        List<Habit_DayOfWeek> habit_dayOfWeeks = new ArrayList<>();
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(habits.getHabit_id(), dayOfWeek);
        if(habit_dayOfWeeks.size() > 0){
           Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
           habit_dayOfWeek.setIsFailed(true);
        }
    }

    public void checkDoneItem(int position) {
        Habits habits = itemList.get(position);
        List<Habit_DayOfWeek> habit_dayOfWeeks = new ArrayList<>();
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(habits.getHabit_id(), dayOfWeek);
        if(habit_dayOfWeeks.size() > 0){
            Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
            if(habits.getTimePerDay() > habit_dayOfWeek.getProgress()){
                habit_dayOfWeek.setProgress(habit_dayOfWeek.getProgress() + 1);
                if(habit_dayOfWeek.getProgress() == habits.getTimePerDay()){
                    habit_dayOfWeek.setIsDone(true);
                }
            }
        }
    }
    public void updateDayOfWeek(DayOfWeek dayOfWeek){
        this.dayOfWeek = dayOfWeek;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public RelativeLayout itemColor;
        public ImageView itemIcon;
        public TextView item_Title;
        public TextView item_CurrentProgress;
        public TextView item_timePerDay;
        public Button buttonDelete;
        public Button buttonCheckDone;
        public HabitItemAdapter habitItemAdapter;

        public ItemViewHolder(View itemView, HabitItemAdapter habitItemAdapter) {
            super(itemView);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonCheckDone = itemView.findViewById(R.id.buttonCheckDone);
            itemColor = itemView.findViewById(R.id.habitItem_Color);
            itemIcon = itemView.findViewById(R.id.habitItem_Icon);
            item_Title = itemView.findViewById(R.id.habitItem_Title);
            item_CurrentProgress = itemView.findViewById(R.id.habitItem_CurrentProgress);
            item_timePerDay = itemView.findViewById(R.id.habitItem_TimePerDay);
            this.habitItemAdapter = habitItemAdapter;


            // Set different icons and colors for buttons
            Drawable deleteIcon = ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_launcher_background);
            Drawable checkDoneIcon = ContextCompat.getDrawable(itemView.getContext(), R.drawable.baseline_done_24);
            int deleteColor = ContextCompat.getColor(itemView.getContext(), R.color.purple_200);
            int checkDoneColor = ContextCompat.getColor(itemView.getContext(), R.color.teal_200);

            buttonDelete.setCompoundDrawablesWithIntrinsicBounds(deleteIcon, null, null, null);
            buttonCheckDone.setCompoundDrawablesWithIntrinsicBounds(checkDoneIcon, null, null, null);
            buttonDelete.setBackgroundColor(deleteColor);
            buttonCheckDone.setBackgroundColor(checkDoneColor);
        }
        public HabitItemAdapter getAdapter(){
            return habitItemAdapter;
        }
    }
}
