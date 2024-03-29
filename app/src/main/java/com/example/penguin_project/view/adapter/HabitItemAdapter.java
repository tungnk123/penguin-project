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
import com.example.penguin_project.model.repo.local.Table.Tree;
import com.example.penguin_project.view.fragment.TrackerFragment;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    public void setHabits(List<Habits> habitsList) {
        this.itemList = habitsList;
        notifyDataSetChanged();
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
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(itemName.getHabit_id(), dayOfWeek.getValue());
        if (habit_dayOfWeeks.size() > 0) {
            Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
            holder.item_CurrentProgress.setText(String.valueOf(habit_dayOfWeek.getProgress()));
        } else {
            holder.item_CurrentProgress.setText("0");
        }
        holder.itemColor.setBackgroundResource(itemName.getColor());
        holder.item_Title.setText(itemName.getTitle());
        holder.itemIcon.setImageResource(itemName.getIcon());
        holder.item_timePerDay.setText(String.valueOf(itemName.getTimePerDay()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(itemName);
                }
            }
        });
        Tree plant = HabitDataBase.getInstance(context).habitDAO().getTreeForestById(itemName.getTree_id());
        holder.imgPlantIcon.setImageResource(plant.getIcon());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void removeItem(int position) {
        Habits habits = itemList.get(position);
        List<Habit_DayOfWeek> habit_dayOfWeeks = new ArrayList<>();
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(habits.getHabit_id(), dayOfWeek.getValue());
        if (habit_dayOfWeeks.size() > 0) {
            Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
            if (habit_dayOfWeek.getProgress() == 0) {
                if(habit_dayOfWeek.getIsFailed()){
                    habit_dayOfWeek.setIsFailed(false);
                    habit_dayOfWeek.setIsDone(false);
                }
                else {
                    habit_dayOfWeek.setIsFailed(true);
                }
            } else {
                habit_dayOfWeek.setProgress(habit_dayOfWeek.getProgress() - 1);
                habit_dayOfWeek.setIsFailed(false);
                habit_dayOfWeek.setIsDone(false);
            }
            HabitDataBase.getInstance(context).habitDAO().updateHabit_DayOfWeek(habit_dayOfWeek);
        }
    }

    public void checkDoneItem(int position) {
        Habits habits = itemList.get(position);
        List<Habit_DayOfWeek> habit_dayOfWeeks = new ArrayList<>();
        habit_dayOfWeeks = HabitDataBase.getInstance(context).habitDAO().findHabitDOWByID(habits.getHabit_id(), dayOfWeek.getValue());
        if (habit_dayOfWeeks.size() > 0) {
            Habit_DayOfWeek habit_dayOfWeek = habit_dayOfWeeks.get(0);
            if (habits.getTimePerDay() > habit_dayOfWeek.getProgress()) {
                habit_dayOfWeek.setProgress(habit_dayOfWeek.getProgress() + 1);
                if(habit_dayOfWeek.getIsFailed()){
                    habit_dayOfWeek.setIsFailed(false);
                }
                HabitDataBase.getInstance(context).habitDAO().updateHabit_DayOfWeek(habit_dayOfWeek);
                if (habit_dayOfWeek.getProgress() == habits.getTimePerDay()) {
                    habit_dayOfWeek.setIsDone(true);
                    habit_dayOfWeek.setIsFailed(false);
                    HabitDataBase.getInstance(context).habitDAO().updateHabit_DayOfWeek(habit_dayOfWeek);
                }
            }
        }
    }

    public void updateDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public interface OnItemClickListener {
        void onItemClick(Habits habit);
    }

    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
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

        public RelativeLayout plantCircle;
        public ImageView imgPlantIcon;

        public ItemViewHolder(View itemView, HabitItemAdapter habitItemAdapter) {
            super(itemView);

            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonCheckDone = itemView.findViewById(R.id.buttonCheckDone);
            itemColor = itemView.findViewById(R.id.rll_habitItem_habitCircle);
            itemIcon = itemView.findViewById(R.id.img_habitItem_habitIcon);
            item_Title = itemView.findViewById(R.id.habitItem_Title);
            plantCircle = itemView.findViewById(R.id.rll_habitItem_plantCircle);
            imgPlantIcon = itemView.findViewById(R.id.img_habitItem_plantIcon); // Updated ID

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


        public HabitItemAdapter getAdapter() {
            return habitItemAdapter;
        }
    }
}
