package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RadioButton;

import com.example.penguin_project.model.data.ThemeControl;
import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsLanguageActivity extends AppCompatActivity {
    ImageButton btnBack;

    RadioButton rabtnEnglish;
    RadioButton rabtnTiengViet;
    RadioButton rabtnTrungQuoc;
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
        setContentView(R.layout.activity_settings_language);
        btnBack = findViewById(R.id.btn_activitySettingsLanguage_btnBack);
        rabtnEnglish = findViewById(R.id.ra_activitySettingsLanguage_english);
        rabtnTiengViet = findViewById(R.id.ra_activitySettingsForest_tiengViet);
        rabtnTrungQuoc = findViewById(R.id.ra_activitySettingslanguage_tiengTrung);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsLanguageActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });
        String language = MenuFragment.languageSettingsSP.getString("language_setting", "English");
        switch (language) {
            case "English":
                rabtnEnglish.setChecked(true);
                break;
            case "Tiếng việt":
                rabtnTiengViet.setChecked(true);
                break;
            case "中国人":
                rabtnTrungQuoc.setChecked(true);
                break;

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = MenuFragment.languageSettingsSP.edit();
        if (rabtnEnglish.isChecked()) {
            editor.putString("language_setting", "English");
        }
        else if (rabtnTiengViet.isChecked()) {
            editor.putString("language_setting", "Tiếng việt");
        }
        else {
            editor.putString("language_setting", "中国人");
        }
        // TODO thay doi language cua he thong o day
        editor.apply();
    }
}