package com.example.penguin_project.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.MainActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsWeekStartAt extends AppCompatActivity {

    ImageButton btnBack;

    RadioButton rabtnMonday;
    RadioButton rabtnSunday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(ThemeControl.getInstance(getApplicationContext()).getData("Mode", -1) == 1){
            setTheme(R.style.AppTheme_Dark);
        }
        else {
            setTheme(R.style.AppTheme_Light);
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
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
        editor.apply();
    }
}