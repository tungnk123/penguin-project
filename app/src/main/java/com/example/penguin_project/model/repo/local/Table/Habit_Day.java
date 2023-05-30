package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateConverter;

import java.time.LocalDate;

@Entity(tableName = "Habit_Day")
@TypeConverters(LocalDateConverter.class)
public class Habit_Day {
    @PrimaryKey
    private LocalDate Habit_Day_id;
    private boolean IsDone;

    public LocalDate getHabit_Day_id() {
        return Habit_Day_id;
    }

    public void setHabit_Day_id(LocalDate habit_Day_id) {
        Habit_Day_id = habit_Day_id;
    }

    public boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(boolean IsDone) {
        this.IsDone = IsDone;
    }

    public Habit_Day(LocalDate Habit_Day_id, boolean IsDone) {
        this.Habit_Day_id = Habit_Day_id;
        this.IsDone = IsDone;
    }
}
