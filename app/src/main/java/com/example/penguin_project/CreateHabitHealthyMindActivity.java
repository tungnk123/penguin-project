package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CreateHabitHealthyMindActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_meditation, btn_connectToNature, btn_drinkWater, btn_goOutForWalk, btn_relax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}