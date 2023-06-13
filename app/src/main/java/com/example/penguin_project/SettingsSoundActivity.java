package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsSoundActivity extends AppCompatActivity {

    ImageButton btnBack;

    RadioButton rabtnChill;
    RadioButton rabtnFire;
    RadioButton rabtnPiano;
    RadioButton rabtnRain;
    RadioButton rabtnGuitar;

    RadioButton rabtnNope;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_sound);
        btnBack = findViewById(R.id.btn_activitySettingsSound_btnBack);

        rabtnChill = findViewById(R.id.ra_activitySettingsSound_chillMusic);
        rabtnFire = findViewById(R.id.ra_activitySettingsSound_fireCamp);
        rabtnGuitar = findViewById(R.id.ra_activitySettingsSound_guitar);
        rabtnPiano = findViewById(R.id.ra_activitySettingsSound_piano);
        rabtnRain = findViewById(R.id.ra_activitySettingsSound_rain);
        rabtnNope = findViewById(R.id.ra_activitySettingsSound_nope);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsSoundActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        String music = MenuFragment.soundSettingsSP.getString("sound_setting", "Chill music");
        switch (music) {
            case "Chill music":
                rabtnChill.setChecked(true);
                break;
            case "Fire camp":
                rabtnFire.setChecked(true);
                break;
            case "Piano":
                rabtnPiano.setChecked(true);
                break;
            case "Rain":
                rabtnRain.setChecked(true);
                break;
            case "Guitar":
                rabtnGuitar.setChecked(true);
                break;
            case "Nope":
                rabtnNope.setChecked(true);
                break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.soundSettingsSP.edit();
        if (rabtnChill.isChecked()) {
            editor.putString("sound_setting", "Chill music");
        }
        if (rabtnNope.isChecked()) {
            editor.putString("sound_setting", "Nope");
        }
        if (rabtnGuitar.isChecked()) {
            editor.putString("sound_setting", "Guitar");
        }
        if (rabtnPiano.isChecked()) {
            editor.putString("sound_setting", "Piano");
        }
        if (rabtnRain.isChecked()) {
            editor.putString("sound_setting", "Rain");
        }
        if (rabtnFire.isChecked()) {
            editor.putString("sound_setting", "Fire camp");
        }
        // TODO xu ly sound o day
        editor.apply();
    }
}