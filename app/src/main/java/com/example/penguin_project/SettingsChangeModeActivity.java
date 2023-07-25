package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsChangeModeActivity extends AppCompatActivity {

    ImageButton btnBack;
    RadioButton rabtnLightMode;
    RadioButton rabtnDarkMode;

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
        setContentView(R.layout.activity_settings_change_mode);
        btnBack = findViewById(R.id.btn_activitySettingsCM_btnBack);
        rabtnLightMode = findViewById(R.id.ra_activitySettingsCM_lightMode);
        rabtnDarkMode = findViewById(R.id.ra_activitySettingsCM_darkMode);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsChangeModeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        if (MenuFragment.modeSettingsSP.getString("mode_setting", "Dark mode").equals("Dark mode")) {
            rabtnDarkMode.setChecked(true);
            ThemeControl.getInstance(getApplicationContext()).saveData("Mode", 1);
            setTheme(R.style.AppTheme_Dark);
            recreate();
        }
        else {
            rabtnLightMode.setChecked(true);
            setTheme(R.style.AppTheme_Light);
            ThemeControl.getInstance(getApplicationContext()).saveData("Mode", 0);
            recreate();
        }

        rabtnDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(rabtnDarkMode.isChecked()){
                    ThemeControl.getInstance(getApplicationContext()).saveData("Mode", 1);
                    setTheme(R.style.AppTheme_Dark);
                    recreate();
                }
                else {
                    ThemeControl.getInstance(getApplicationContext()).saveData("Mode", 0);
                    setTheme(R.style.AppTheme_Light);
                    recreate();
                }
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.modeSettingsSP.edit();
        editor.putString("mode_setting", rabtnDarkMode.isChecked() ? "Dark mode" : "Light mode");

        // TODO xu ly thay doi che do sang toi o day
        editor.apply();
    }

}