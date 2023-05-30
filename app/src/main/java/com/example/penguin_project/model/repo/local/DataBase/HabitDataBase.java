package com.example.penguin_project.model.repo.local.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.penguin_project.model.repo.local.Table.HabitGroup;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.RemindTime;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;

@Database(entities = {Habits.class, Habit_Day.class, Habit_DayOfWeek.class, HabitGroup.class, RemindTime.class, TimeOfDay.class}, version = 1)
public abstract class HabitDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "Habit.db";
    private static HabitDataBase instance;

    public static synchronized HabitDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HabitDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract HabitDAO habitDAO();
}
