package com.example.penguin_project.model.repo.local.DataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.penguin_project.model.repo.local.Table.HabitOfTheme;
import com.example.penguin_project.model.repo.local.Table.Habits;

@Database(entities = HabitOfTheme.class, exportSchema = false, version = 1)
public abstract class HabitThemeDB extends RoomDatabase {

    private static final String DB_NAME = "HabitOfTheme.db";

    private static HabitThemeDB instance;

    public static synchronized HabitThemeDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), HabitThemeDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }

    public abstract HabitOfThemDAO habitOfThemDAO();

}
