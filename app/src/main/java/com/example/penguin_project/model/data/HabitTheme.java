package com.example.penguin_project.model.data;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;
import java.time.LocalDate;

public class HabitTheme implements Serializable {

    String HabitThemeID;
    String HabitThemeName;
    int ImageHabitTheme;

    public HabitTheme(String habitThemeID ,String habitThemeName, int image) {
        HabitThemeID = habitThemeID;
        HabitThemeName = habitThemeName;
        ImageHabitTheme = image;
    }


    public String getHabitThemeID() {
        return HabitThemeID;
    }

    public void setHabitThemeID(String habitThemeID) {
        HabitThemeID = habitThemeID;
    }

    public String getHabitThemeName() {
        return HabitThemeName;
    }

    public void setHabitThemeName(String habitThemeName) {
        HabitThemeName = habitThemeName;
    }

    public int getImageHabitTheme() {
        return ImageHabitTheme;
    }

    public void setImage(int image) {
        ImageHabitTheme = image;
    }

}
