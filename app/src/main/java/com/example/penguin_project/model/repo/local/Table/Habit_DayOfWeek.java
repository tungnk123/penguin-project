package com.example.penguin_project.model.repo.local.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.time.DayOfWeek;

@Entity(tableName = "Habit_DayOfWeek",
        foreignKeys = {
                @ForeignKey(entity = Habits.class, parentColumns = "Habit_id", childColumns = "Habit_id", onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"Habit_DayOfWeek_id", "Habit_id"})
public class Habit_DayOfWeek {
    @NonNull
    private int Habit_DayOfWeek_id;
    @NonNull
    private int Habit_id;
    @NonNull
    private Integer Progress;
    private Boolean IsDone;
    private Boolean IsFailed;

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int Progress) {
        this.Progress = Progress;
    }

    public Boolean getIsFailed() {
        return IsFailed;
    }

    public void setIsFailed(Boolean IsFailed) {
        this.IsFailed = IsFailed;
    }

    public int getHabit_DayOfWeek_id() {
        return Habit_DayOfWeek_id;
    }

    public void setHabit_DayOfWeek_id(int Habit_DayOfWeek_id) {
        this.Habit_DayOfWeek_id = Habit_DayOfWeek_id;
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

    public Habit_DayOfWeek(int Habit_DayOfWeek_id, int Habit_id, boolean IsDone, int Progress, boolean IsFailed) {
        this.Habit_DayOfWeek_id = Habit_DayOfWeek_id;
        this.Habit_id = Habit_id;
        this.IsDone = IsDone;
        this.Progress = Progress;
        this.IsFailed = IsFailed;
    }
}
