package com.example.penguin_project.model.repo.local.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.penguin_project.model.repo.local.Table.HabitGroup;
import com.example.penguin_project.model.repo.local.Table.Habit_Day;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.RemindTime;
import com.example.penguin_project.model.repo.local.Table.Steps;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;
import com.example.penguin_project.model.repo.local.Table.Todo;

@Database(entities = {Habits.class, Habit_Day.class, Habit_DayOfWeek.class, HabitGroup.class, RemindTime.class, TimeOfDay.class, Todo.class, Steps.class}, version = 2)
public abstract class HabitDataBase extends RoomDatabase {

    private static final String DATABASE_NAME = "Habit.db";
    private static HabitDataBase instance;

    public static synchronized HabitDataBase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HabitDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Habit_DayOfWeek ADD COLUMN Progress INTEGER DEFAULT 0 NOT NULL");
            database.execSQL("ALTER TABLE Habit_DayOfWeek ADD COLUMN IsFailed boolean");
        }
    };

    public abstract HabitDAO habitDAO();
}
