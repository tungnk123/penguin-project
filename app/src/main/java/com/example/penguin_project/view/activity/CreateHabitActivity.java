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

public class CreateHabitActivity extends AppCompatActivity {

    RelativeLayout btn_addNewHabit, btn_habitTrending, btn_habitHealthyMind, btn_habitGoodNight, btn_habitFocus, btn_habitSaving;
    ImageButton btn_back;
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
        setContentView(R.layout.activity_create_habit);


        settingButton();
    }

    private void settingButton() {
        btn_addNewHabit = findViewById(R.id.CreateHabit_btnAddNewHabit);
        btn_habitTrending = findViewById(R.id.CreateHabit_btnHabitTrending);
        btn_habitHealthyMind = findViewById(R.id.CreateHabit_btnHabitHealthyMind);
        btn_habitGoodNight = findViewById(R.id.CreateHabit_btnHabitGoodNight);
        btn_habitFocus = findViewById(R.id.CreateHabit_btnHabitFocus);
        btn_habitSaving = findViewById(R.id.CreateHabit_btnHabitSaving);
        btn_back = findViewById(R.id.CreateHabit_btn_back);

        btn_addNewHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.setAction("Blank habit");
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_habitTrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateHabitTrendingActivity.class);
                startActivity(intent);
            }
        });
        btn_habitHealthyMind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateHabitHealthyMindActivity.class);
                startActivity(intent);
            }
        });
        btn_habitGoodNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateHabitGoodNightActivity.class);
                startActivity(intent);
            }
        });
        btn_habitFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateHabitFocusActivity.class);
                startActivity(intent);
            }
        });
        btn_habitSaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateHabitSavingActivity.class);
                startActivity(intent);
            }
        });
    }
}