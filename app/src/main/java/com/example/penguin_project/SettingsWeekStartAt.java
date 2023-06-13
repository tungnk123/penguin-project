package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsWeekStartAt extends AppCompatActivity {

    ImageButton btnBack;

    RadioButton rabtnMonday;
    RadioButton rabtnSunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_week_start_at);
        btnBack = findViewById(R.id.btn_activitySettingsWeek_btnBack);

        rabtnMonday = findViewById(R.id.ra_activitySettingsWeek_monday);
        rabtnSunday = findViewById(R.id.ra_activitySettingsWeek_sunday);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsWeekStartAt.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        if (MenuFragment.weekSettingsSP.getString("week_setting", "Monday").equals("Monday")) {
            rabtnMonday.setChecked(true);
        }
        else {
            rabtnSunday.setChecked(true);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.weekSettingsSP.edit();
        editor.putString("week_setting", rabtnMonday.isChecked() ? "Monday": "Sunday");

        // TODO xu ly thay doi ngay dau tuan o day
        editor.apply();
    }
}