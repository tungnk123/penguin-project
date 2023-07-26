package com.example.penguin_project.model.data;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeControl {
    private static final String SHARED_PREFERENCES_NAME = "Theme Control";
    private static ThemeControl instance;
    private SharedPreferences sharedPreferences;

    private ThemeControl(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized ThemeControl getInstance(Context context) {
        if (instance == null) {
            instance = new ThemeControl(context);
        }
        return instance;
    }

    // Phương thức để lưu dữ liệu vào SharedPreferences
    public void saveData(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    // Phương thức để đọc dữ liệu từ SharedPreferences
    public int getData(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }
}
