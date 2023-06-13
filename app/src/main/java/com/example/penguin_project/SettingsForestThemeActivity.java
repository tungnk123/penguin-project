package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsForestThemeActivity extends AppCompatActivity {


    ImageButton btnBack;
    RadioButton rabtnPlain;
    RadioButton rabtnPine;
    RadioButton rabtnSand;
    RadioButton rabtnSwamp;
    RadioButton rabtnFlower;
    RadioButton rabtnBeach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_forest_theme);
        btnBack = findViewById(R.id.btn_activitySettingsForest_btnBack);
        rabtnSand = findViewById(R.id.ra_activitySettingsForest_sand);
        rabtnBeach = findViewById(R.id.ra_activitySettingsForest_beach);
        rabtnFlower = findViewById(R.id.ra_activitySettingsForest_flower);
        rabtnPine = findViewById(R.id.ra_activitySettingsForest_pine);
        rabtnPlain = findViewById(R.id.ra_activitySettingsForest_plain);
        rabtnSwamp = findViewById(R.id.ra_activitySettingsForest_swamp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsForestThemeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        String forestTheme = MenuFragment.forestThemeSettingsSP.getString("forest_setting", "Plain");
        switch (forestTheme) {
            case "Plain":
                rabtnPlain.setChecked(true);
                break;
            case "Pine forest":
                rabtnPine.setChecked(true);
                break;
            case "Sand":
                rabtnSand.setChecked(true);
                break;
            case "Swamp":
                rabtnSwamp.setChecked(true);
                break;
            case "Flower garden":
                rabtnFlower.setChecked(true);
                break;
            case "Beach":
                rabtnBeach.setChecked(true);
                break;
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.forestThemeSettingsSP.edit();
        if (rabtnPlain.isChecked()) {
            editor.putString("forest_setting", "Plain");
        }
        if (rabtnPine.isChecked()) {
            editor.putString("forest_setting", "Pine forest");
        }
        if (rabtnSand.isChecked()) {
            editor.putString("forest_setting", "Sand");
        }
        if (rabtnSwamp.isChecked()) {
            editor.putString("forest_setting", "Swamp");
        }
        if (rabtnFlower.isChecked()) {
            editor.putString("forest_setting", "Flower garden");
        }
        if (rabtnBeach.isChecked()) {
            editor.putString("forest_setting", "Beach");
        }
        // TODO xu ly thay doi forest theme o day
        editor.apply();
    }
}