package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.StoreItem;
import com.example.penguin_project.view.fragment.MenuFragment;
import com.example.penguin_project.viewmodel.StoreItemViewModel;

import java.util.List;

public class SettingsSoundActivity extends AppCompatActivity {

    ImageButton btnBack;

    RadioButton rabtnChill;
    RadioButton rabtnFire;
    RadioButton rabtnPiano;
    RadioButton rabtnRain;
    RadioButton rabtnGuitar;

    RadioButton rabtnNope;

    StoreItemViewModel storeItemViewModel;
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

        storeItemViewModel = new ViewModelProvider(this).get(StoreItemViewModel.class);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsSoundActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

        String music = MenuFragment.soundSettingsSP.getString("sound_setting", "Chill music");
        List< StoreItem> musicList = storeItemViewModel.getMusicListNotLiveData();
        if (!musicList.get(1).getIsPurchased()) {
            rabtnFire.setClickable(false);
            rabtnFire.setPaintFlags(rabtnFire.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!musicList.get(0).getIsPurchased()) {
            rabtnGuitar.setClickable(false);
            rabtnGuitar.setPaintFlags(rabtnGuitar.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!musicList.get(2).getIsPurchased()) {
            rabtnRain.setClickable(false);
            rabtnRain.setPaintFlags(rabtnRain.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        if (!musicList.get(3).getIsPurchased()) {
            rabtnPiano.setClickable(false);
            rabtnPiano.setPaintFlags(rabtnPiano.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

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