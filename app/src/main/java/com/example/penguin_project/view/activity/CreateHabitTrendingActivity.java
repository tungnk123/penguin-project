package com.example.penguin_project.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.ThemeControl;

public class CreateHabitTrendingActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_eatHealthyMeal, btn_study, btn_exercise, btn_cleanTheHouse, btn_makeYourBed;
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

        btn_eatHealthyMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Eat healthy meal");
                intent.putExtra("Icon", R.mipmap.icon_healthy_meal);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Study");
                intent.putExtra("Icon", R.mipmap.icon_study);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_exercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Exercise");
                intent.putExtra("Icon", R.mipmap.icon_exercise);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_cleanTheHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Clean the house");
                intent.putExtra("Icon", R.mipmap.icon_clean_the_house);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_makeYourBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Make your bed");
                intent.putExtra("Icon", R.mipmap.icon_make_bed);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
    }
}