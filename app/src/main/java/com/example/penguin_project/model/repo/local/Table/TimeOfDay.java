package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalTime;

@Entity(tableName = "TimeOfDay")
public class TimeOfDay {
    @PrimaryKey
    private int TimeOfDay_id;
    private String TimeOfDay;

    public int getTimeOfDay_id() {
        return TimeOfDay_id;
    }

    public void setTimeOfDay_id(int timeOfDay_id) {
        TimeOfDay_id = timeOfDay_id;
    }

    public String getTimeOfDay() {
        return TimeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        TimeOfDay = timeOfDay;
    }

    public TimeOfDay(int TimeOfDay_id, String TimeOfDay) {
        this.TimeOfDay_id = TimeOfDay_id;
        this.TimeOfDay = TimeOfDay;
    }
}
