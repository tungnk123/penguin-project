package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateConverter;
import com.example.penguin_project.model.data.LocalDateTimeConverter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "Habits", foreignKeys = {
        @ForeignKey(entity = TimeOfDay.class, parentColumns = "TimeOfDay_id", childColumns = "TimeOfDay_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Tree.class, parentColumns = "Tree_id", childColumns = "Tree_id", onDelete = ForeignKey.CASCADE)
})
@TypeConverters({LocalDateConverter.class, LocalDateTimeConverter.class})
public class Habits implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int Habit_id;
    private String Title;
    private int TimeOfDay_id;
    private int TimePerDay;
    private int Color;
    private int Icon;
    private LocalDate CreateDay;
    private int CurrentStreak;
    private int MaxStreak;

    private int Tree_id;
    private LocalDateTime HourOfDay;

    public LocalDateTime getHourOfDay() {
        return HourOfDay;
    }

    public void setHourOfDay(LocalDateTime hourOfDay) {
        HourOfDay = hourOfDay;
    }

    public Habits(String title, int timeOfDay_id, int timePerDay, int color, int icon, LocalDate createDay, int currentStreak, int maxStreak, int tree_id, LocalDateTime hourOfDay) {
        Title = title;
        TimeOfDay_id = timeOfDay_id;
        TimePerDay = timePerDay;
        Color = color;
        Icon = icon;
        CreateDay = createDay;
        CurrentStreak = currentStreak;
        MaxStreak = maxStreak;
        Tree_id = tree_id;
        HourOfDay = hourOfDay;
    }

    public int getTree_id() {
        return Tree_id;
    }

    public void setTree_id(int Tree_id) {
        this.Tree_id = Tree_id;
    }

    public Habits(String Title, int TimeOfDay_id, int TimePerDay, int Color, int Icon, LocalDate CreateDay, int CurrentStreak, int MaxStreak) {
        this.Title = Title;
        this.TimeOfDay_id = TimeOfDay_id;
        this.TimePerDay = TimePerDay;
        this.Color = Color;
        this.Icon = Icon;
        this.CreateDay = CreateDay;
        this.CurrentStreak = CurrentStreak;
        this.MaxStreak = MaxStreak;
    }

    public int getHabit_id() {
        return Habit_id;
    }

    public void setHabit_id(int habit_id) {
        Habit_id = habit_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getTimeOfDay_id() {
        return TimeOfDay_id;
    }

    public void setTimeOfDay_id(int timeOfDay_id) {
        TimeOfDay_id = timeOfDay_id;
    }

    public int getTimePerDay() {
        return TimePerDay;
    }

    public void setTimePerDay(int timePerDay) {
        TimePerDay = timePerDay;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int color) {
        Color = color;
    }

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public LocalDate getCreateDay() {
        return CreateDay;
    }

    public void setCreateDay(LocalDate createDay) {
        CreateDay = createDay;
    }

    public int getCurrentStreak() {
        return CurrentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        CurrentStreak = currentStreak;
    }

    public int getMaxStreak() {
        return MaxStreak;
    }

    public void setMaxStreak(int maxStreak) {
        MaxStreak = maxStreak;
    }
}
