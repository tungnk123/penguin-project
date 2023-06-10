package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CreateHabitTrendingActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_eatHealthyMeal, btn_study, btn_exercise, btn_cleanTheHouse, btn_makeYourBed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit_trending);

        settingButton();
    }

    private void settingButton() {
        btn_back = findViewById(R.id.CreateHabitTrending_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_eatHealthyMeal = findViewById(R.id.CreateHabitTrending_btnHabitHealthyMeal);
        btn_study = findViewById(R.id.CreateHabitTrending_btnHabitStudy);
        btn_exercise = findViewById(R.id.CreateHabitTrending_btnHabitExcercise);
        btn_cleanTheHouse = findViewById(R.id.CreateHabitTrending_btnHabitCleanTheHouse);
        btn_makeYourBed = findViewById(R.id.CreateHabitTrending_btnHabitMakeYourBed);
    }
}