package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsAlarmActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_settings_alarm);
        btnBack = findViewById(R.id.btn_activitySettingsAlarm_btnBack);
        rabtnOn = findViewById(R.id.ra_activitySettingsAlarm_on);
        rabtnOff = findViewById(R.id.ra_activitySettingsAlarm_off);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsAlarmActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });
        if (MenuFragment.alarmSettingsSP.getString("alarm_setting", "On").equals("On")) {
            rabtnOn.setChecked(true);
        }
        else {
            rabtnOff.setChecked(true);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.alarmSettingsSP.edit();
        editor.putString("alarm_setting", rabtnOn.isChecked() ? "On" : "Off");
        // TODO: Xu ly xu kien alarm on or off o day
        editor.apply();
    }
}