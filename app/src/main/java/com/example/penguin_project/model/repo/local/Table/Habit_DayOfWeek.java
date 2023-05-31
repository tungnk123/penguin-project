package com.example.penguin_project.model.repo.local.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.DayOfWeek;

@Entity(tableName = "Habit_DayOfWeek", indices = {@Index(value = {"Habit_DayOfWeek_id", "Habit_id"}, unique = true)},
       foreignKeys = {
        @ForeignKey(entity = Habits.class, parentColumns = "Habit_id", childColumns = "Habit_id", onDelete = ForeignKey.CASCADE)
       }
)
public class Habit_DayOfWeek {
    @PrimaryKey
    @NonNull
    private DayOfWeek Habit_DayOfWeek_id;
    private int Habit_id;

    private int Progress;
    private Boolean IsDone;

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int Progress) {
        this.Progress = Progress;
    }

    public DayOfWeek getHabit_DayOfWeek_id() {
        return Habit_DayOfWeek_id;
    }

    public void setHabit_DayOfWeek_id(DayOfWeek habit_DayOfWeek_id) {
        Habit_DayOfWeek_id = habit_DayOfWeek_id;
    }

    public int getHabit_id() {
        return Habit_id;
    }

    public void setHabit_id(int habit_id) {
        Habit_id = habit_id;
    }

    public Boolean getIsDone() {
        return IsDone;
    }

    public void setIsDone(Boolean IsDone) {
        this.IsDone = IsDone;
    }

    public Habit_DayOfWeek(DayOfWeek Habit_DayOfWeek_id, int Habit_id, boolean IsDone, int Progress) {
        this.Habit_DayOfWeek_id = Habit_DayOfWeek_id;
        this.Habit_id = Habit_id;
        this.IsDone = IsDone;
        this.Progress = Progress;
    }
}
