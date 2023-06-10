package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

        btn_thinkAboutYourDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Think about your day");
                intent.putExtra("Icon", R.mipmap.icon_think_about_your_day);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_dontTouchPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Don't touch phone");
                intent.putExtra("Icon", R.mipmap.icon_dont_touch_phone);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_sleep8Hour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Sleep 8 hour");
                intent.putExtra("Icon", R.mipmap.icon_sleep_8_hour);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_readToRelax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Read to relax");
                intent.putExtra("Icon", R.mipmap.icon_read_book);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_avoidCaffein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Avoid caffein");
                intent.putExtra("Icon", R.mipmap.icon_avoid_cafein);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
    }

}