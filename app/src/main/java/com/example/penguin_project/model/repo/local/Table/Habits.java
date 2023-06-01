package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateConverter;

import java.time.LocalDate;

@Entity(tableName = "Habits", foreignKeys = {
        @ForeignKey(entity = HabitGroup.class, parentColumns = "Group_id", childColumns = "Group_id", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = TimeOfDay.class, parentColumns = "TimeOfDay_id", childColumns = "TimeOfDay_id", onDelete = ForeignKey.CASCADE)
})
@TypeConverters(LocalDateConverter.class)
public class Habits {
    @PrimaryKey(autoGenerate = true)
    private int Habit_id;
    private String Title;
    private int TimeOfDay_id;
    private int TimePerDay;
    private int Group_id;
    private int Color;
    private int Icon;
    private LocalDate CreateDay;
    private int CurrentStreak;
    private int MaxStreak;

    public Habits(int Habit_id, String Title, int TimeOfDay_id, int TimePerDay, int Group_id, int Color, int Icon, LocalDate CreateDay, int CurrentStreak, int MaxStreak) {
        this.Habit_id = Habit_id;
        this.Title = Title;
        this.TimeOfDay_id = TimeOfDay_id;
        this.TimePerDay = TimePerDay;
        this.Group_id = Group_id;
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

    public int getGroup_id() {
        return Group_id;
    }

    public void setGroup_id(int group_id) {
        Group_id = group_id;
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