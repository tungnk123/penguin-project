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

public class CreateHabitSavingActivity extends AppCompatActivity {
    ImageButton btn_back;
    RelativeLayout btn_payBills, btn_planExpenses, btn_listOfWeeklyShopping, btn_saveIncome, btn_keepTrackOfExpenses;
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

        btn_payBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Pay bills");
                intent.putExtra("Icon", R.mipmap.icon_pay_bills);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_planExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Plan expenses");
                intent.putExtra("Icon", R.mipmap.icon_plan_expenses);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_listOfWeeklyShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "List of Weekly shopping");
                intent.putExtra("Icon", R.mipmap.icon_list_of_weekly_shopping);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_saveIncome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Save income");
                intent.putExtra("Icon", R.mipmap.icon_saving);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
        btn_keepTrackOfExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditHabitActivity.class);
                intent.putExtra("Title", "Keep track of expenses");
                intent.putExtra("Icon", R.mipmap.icon_keep_track_of_expense);
                intent.setAction("New habit");
                startActivity(intent);
            }
        });
    }
}