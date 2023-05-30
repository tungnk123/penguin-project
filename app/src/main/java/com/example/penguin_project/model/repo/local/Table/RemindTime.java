package com.example.penguin_project.model.repo.local.Table;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalTimeConverter;

import java.time.LocalTime;

@Entity(tableName = "RemindTime", indices = {@Index(value = {"Hour", "Habit_id"}, unique = true)},
    foreignKeys = {
            @ForeignKey(entity = Habits.class, parentColumns = "Habit_id", childColumns = "Habit_id", onDelete = ForeignKey.CASCADE)
    }
)
@TypeConverters({LocalTimeConverter.class})
public class RemindTime {
    @PrimaryKey
    @NonNull
    private LocalTime Hour;
    private int Habit_id;

    public LocalTime getHour() {
        return Hour;
    }

    public void setHour(LocalTime hour) {
        Hour = hour;
    }

    public int getHabit_id() {
        return Habit_id;
    }

    public void setHabit_id(int habit_id) {
        Habit_id = habit_id;
    }

    public RemindTime(LocalTime Hour, int Habit_id) {
        this.Hour = Hour;
        this.Habit_id = Habit_id;
    }
}
