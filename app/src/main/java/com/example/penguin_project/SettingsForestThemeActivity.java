package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class SettingsForestThemeActivity extends AppCompatActivity {


    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_forest_theme);
        btnBack = findViewById(R.id.btn_activitySettingsForest_btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backIntent = new Intent(SettingsForestThemeActivity.this, MainActivity.class);
                backIntent.putExtra("SELECTED_FRAGMENT", "setting");
                startActivity(backIntent);
            }
        });
    }
}