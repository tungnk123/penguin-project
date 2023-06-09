package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateTimeConverter;

@Entity(tableName = "HabitOfTheme")
@TypeConverters(LocalDateTimeConverter.class)
public class HabitOfTheme {

    @PrimaryKey(autoGenerate = true)
    private int Habit_id;
    private String Theme_id;
    private String Title;
    private int ThemeImage;

    public HabitOfTheme(int Habit_id , String Theme_id ,String Title, int ThemeImage) {
        this.Habit_id = Habit_id;
        this.Theme_id = Theme_id;
        this.Title = Title;
        this.ThemeImage = ThemeImage;
    }

    public int getHabit_id() {
        return Habit_id;
    }

    public void setHabit_id(int habit_id) {
        Habit_id = habit_id;
    }

    public String getTheme_id() {
        return Theme_id;
    }

    public void setTheme_id(String theme_id) {
        Theme_id = theme_id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getThemeImage() {
        return ThemeImage;
    }

    public void setThemeImage(int themeImage) {
        ThemeImage = themeImage;
    }
}
