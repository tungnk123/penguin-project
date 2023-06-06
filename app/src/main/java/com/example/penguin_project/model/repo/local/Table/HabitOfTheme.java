package com.example.penguin_project.model.repo.local.Table;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.penguin_project.model.data.LocalDateTimeConverter;

@Entity(tableName = "HabitOfTheme")
@TypeConverters(LocalDateTimeConverter.class)
public class HabitOfTheme {

    @PrimaryKey(autoGenerate = true)
    private int NameOfHabit_id;
    private int Theme_id;
    private String Title;
    private int ThemeImage;

    public HabitOfTheme(int NameOfHabit_id , int Theme_id ,String Title, int ThemeImage) {
        this.NameOfHabit_id = NameOfHabit_id;
        this.Theme_id = Theme_id;
        this.Title = Title;
        this.ThemeImage = ThemeImage;
    }

    public int getNameOfHabit_id() {
        return NameOfHabit_id;
    }

    public void setNameOfHabit_id(int nameOfHabit_id) {
        NameOfHabit_id = nameOfHabit_id;
    }

    public int getTheme_id() {
        return Theme_id;
    }

    public void setTheme_id(int theme_id) {
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
