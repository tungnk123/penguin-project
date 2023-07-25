package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.penguin_project.model.data.ThemeControl;

public class CreateHabitHealthyMindActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_meditation, btn_connectToNature, btn_drinkWater, btn_goOutForWalk, btn_relax;
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
        setContentView(R.layout.activity_create_habit_healthy_mind);

        settingButton();
    }

    private void settingButton() {
        btn_back = findViewById(R.id.CreateHabitHealthyMind_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_meditation = findViewById(R.id.CreateHabitHealthyMind_btnHabitMeditation);
        btn_connectToNature = findViewById(R.id.CreateHabitHealthyMind_btnHabitConnectToNature);
        btn_drinkWater = findViewById(R.id.CreateHabitHealthyMind_btnHabitDrinkWater);
        btn_goOutForWalk = findViewById(R.id.CreateHabitHealthyMind_btnHabitGoOutForWalk);
        btn_relax = findViewById(R.id.CreateHabitHealthyMind_btnHabitRelax);

        btn_meditation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Meditation");
                intent.putExtra("Icon", R.mipmap.icon_meditation);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_connectToNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Connect to nature");
                intent.putExtra("Icon", R.mipmap.icon_connect_nature);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_drinkWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Drink water");
                intent.putExtra("Icon", R.mipmap.icon_drink_water);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_goOutForWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Go out for walk");
                intent.putExtra("Icon", R.mipmap.icon_walk);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_relax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Relax");
                intent.putExtra("Icon", R.mipmap.icon_relax);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
    }
}