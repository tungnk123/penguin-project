package com.example.penguin_project.model.data;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.view.fragment.MenuFragment;

import java.time.LocalDate;
import java.util.List;

public class UpdateHabitReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        List<Habit_DayOfWeek> habitsList = HabitDataBase.getInstance(context).habitDAO().getListHabitDowByDow(LocalDate.now().getDayOfWeek().getValue());
        int numberHabitsDOWfalse = 0;
        for(Habit_DayOfWeek habit_dayOfWeek : habitsList){
            if(!habit_dayOfWeek.getIsDone()){
                numberHabitsDOWfalse++;
                // update habit_streak
            }
            else{
                List<Habits> listHabit = HabitDataBase.getInstance(context).habitDAO().getHabitsByID(habit_dayOfWeek.getHabit_id());
                Habits habits = listHabit.get(0);
                habits.setCurrentStreak(habits.getCurrentStreak() + 1);
                if(habits.getMaxStreak() <= habits.getCurrentStreak()){
                    habits.setMaxStreak(habits.getCurrentStreak());
                }
                HabitDataBase.getInstance(context).habitDAO().updateHabits(habits);
                Toast.makeText(context, String.valueOf(habits.getHabit_id()), Toast.LENGTH_SHORT).show();
            }
        }
        // update habit_day
        LocalDate day = LocalDate.now();
        Long dateInMillis = LocalDateConverter.toTimestamp(day);
        List<Habit_Day> habit_days = HabitDataBase.getInstance(context).habitDAO().getHabit_DayByID(dateInMillis);
        Habit_Day habit_day = habit_days.get(0);
        if(numberHabitsDOWfalse > 0){
            if (MenuFragment.vacationModeSettingsSP.getString("vacation_setting", "Off").equals("Off")) {
                habit_day.setIsDone(false);
            }
            else {
                habit_day.setIsDone(true);
            }

            if (MenuFragment.diseaseModeSettingsSP.getString("disease_setting", "Off").equals("Off")) {
                habit_day.setIsDone(false);
            }
            else {
                habit_day.setIsDone(true);
            }


            HabitDataBase.getInstance(context).habitDAO().updateHabit_Day(habit_day);
        }
        else {
            habit_day.setIsDone(true);
            int NewMoney = CoinManager.getInstance(context).getData("Coin", 100) + 20;
            CoinManager.getInstance(context).saveData("Coin", NewMoney);
            HabitDataBase.getInstance(context).habitDAO().updateHabit_Day(habit_day);
        }
    }
}
