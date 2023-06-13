package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.penguin_project.view.fragment.MenuFragment;

public class SettingsChangeModeActivity extends AppCompatActivity {

    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_change_mode);
        btnBack = findViewById(R.id.btn_activitySettingsCM_btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsChangeModeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });
    }

}