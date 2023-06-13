package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsAlarmActivity extends AppCompatActivity {

    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_alarm);
        btnBack = findViewById(R.id.btn_activitySettingsAlarm_btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsAlarmActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });

    }
}