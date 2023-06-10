package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class CreateHabitSavingActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_payBills, btn_planExpenses, btn_listOfWeeklyShopping, btn_saveIncome, btn_keepTrackOfExpenses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit_saving);

        settingButton();
    }

    private void settingButton() {
        btn_back = findViewById(R.id.CreateHabitSaving_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_payBills = findViewById(R.id.CreateHabitSaving_btnHabitPayBills);
        btn_planExpenses = findViewById(R.id.CreateHabitSaving_btnHabitPlanExpenses);
        btn_listOfWeeklyShopping = findViewById(R.id.CreateHabitSaving_btnHabitListOfWeeklyShopping);
        btn_saveIncome = findViewById(R.id.CreateHabitSaving_btnHabitSaveIncome);
        btn_keepTrackOfExpenses = findViewById(R.id.CreateHabitSaving_btnHabitKeepTrackOfExpenses);
    }
}