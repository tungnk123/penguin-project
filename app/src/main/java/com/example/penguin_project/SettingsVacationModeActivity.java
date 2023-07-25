package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsVacationModeActivity extends AppCompatActivity {

    ImageButton btnBack;
    RadioButton rabtnOn;
    RadioButton rabtnOff;
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
        setContentView(R.layout.activity_settings_vacation_mode);
        btnBack = findViewById(R.id.btn_activitySettingsVM_btnBack);
        rabtnOff = findViewById(R.id.ra_activitySettingsVM_off);
        rabtnOn = findViewById(R.id.ra_activitySettingsVM_on);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsVacationModeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        if (MenuFragment.vacationModeSettingsSP.getString("vacation_setting", "Off").equals("Off")) {
            rabtnOff.setChecked(true);
        }
        else {
            rabtnOn.setChecked(true);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.vacationModeSettingsSP.edit();
        editor.putString("vacation_setting", rabtnOn.isChecked() ? "On": "Off");

        // TODO xu ly vacation mode o day
        editor.apply();
    }
}