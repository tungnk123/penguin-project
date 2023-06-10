package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CreateHabitGoodNightActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_thinkAboutYourDay, btn_dontTouchPhone, btn_sleep8Hour, btn_readToRelax, btn_avoidCaffein;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit_good_night);

        settingButton();
    }

    private void settingButton() {
        btn_back = findViewById(R.id.CreateHabitGoodNight_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_thinkAboutYourDay = findViewById(R.id.CreateHabitGoodNight_btnHabitThinkYourDay);
        btn_dontTouchPhone = findViewById(R.id.CreateHabitGoodNight_btnHabitDontTouchPhone);
        btn_sleep8Hour = findViewById(R.id.CreateHabitGoodNight_btnHabitSleep8Hour);
        btn_readToRelax = findViewById(R.id.CreateHabitGoodNight_btnHabitReadToRelax);
        btn_avoidCaffein = findViewById(R.id.CreateHabitGoodNight_btnHabitAvoidCafein);
    }

}