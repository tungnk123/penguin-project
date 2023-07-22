package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsDiseaseModeActivity extends AppCompatActivity {

    ImageButton btnBack;
    RadioButton rabtnOn;
    RadioButton rabtnOff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_disease_mode);
        btnBack = findViewById(R.id.btn_activitySettingsDM_btnBack);
        rabtnOn = findViewById(R.id.ra_activitySettingsDM_on);
        rabtnOff = findViewById(R.id.ra_activitySettingsDM_off);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsDiseaseModeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        if (MenuFragment.diseaseModeSettingsSP.getString("disease_setting", "Off").equals("Off")) {
            rabtnOff.setChecked(true);
        }
        else {
            rabtnOn.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.diseaseModeSettingsSP.edit();
        editor.putString("disease_setting", rabtnOn.isChecked() ? "On": "Off");

        // TODO xu ly disease mode o day
        editor.apply();
    }
}