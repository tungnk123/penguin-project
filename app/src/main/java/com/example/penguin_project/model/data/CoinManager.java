package com.example.penguin_project.model.data;

import android.content.Context;
import android.content.SharedPreferences;

public class CoinManager {
    private static final String SHARED_PREFERENCES_NAME = "Coin Storage";
    private static CoinManager instance;
    private SharedPreferences sharedPreferences;

    private CoinManager(Context context) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized CoinManager getInstance(Context context) {
        if (instance == null) {
            instance = new CoinManager(context);
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
