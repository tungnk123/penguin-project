package com.example.penguin_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;

import java.util.List;

public class HabitsInfoActivity extends AppCompatActivity {

    ToggleButton tgb2, tgb3, tgb4, tgb5, tgb6, tgb7, tgbCN;
    LinearLayout btn_anytime, btn_morning, btn_afternoon, btn_evening;
    TextView HabitsTitle, HabitTimePerDay;
    RelativeLayout HabitColor;
    ImageView HabitIcon;
    ImageButton btn_back, btn_deleteHabit, btn_editHabit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_info);

        setUpToggleButton();

        btn_anytime = findViewById(R.id.Habits_info_btnAnytime);
        btn_morning = findViewById(R.id.Habits_info_btnMorning);
        btn_afternoon = findViewById(R.id.Habits_info_btnAfternoon);
        btn_evening = findViewById(R.id.Habits_info_btnEvening);

        HabitsTitle = findViewById(R.id.HabitInfo_habitItem_Title);
        HabitTimePerDay = findViewById(R.id.Habits_info_timePerDay);
        HabitColor = findViewById(R.id.HabitInfo_habitItem_Color);
        HabitIcon = findViewById(R.id.HabitInfo_habitItem_Icon);

        btn_back = findViewById(R.id.HabitsInfo_btn_back);
        btn_deleteHabit = findViewById(R.id.HabitsInfo_btn_deleteHabits);
        btn_editHabit = findViewById(R.id.HabitsInfo_btn_editHabits);

        Intent intent = getIntent();
        Habits habits = (Habits)intent.getSerializableExtra("habitKey");

        switch (habits.getTimeOfDay_id()){
            case 1: btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
            break;
            case 2: btn_morning.setBackgroundResource(R.drawable.item_timeofday_shape);
            break;
            case 3: btn_afternoon.setBackgroundResource(R.drawable.item_timeofday_shape);
            break;
            case 4: btn_evening.setBackgroundResource(R.drawable.item_timeofday_shape);
            break;
        }

        List<Habit_DayOfWeek> listHabitDow = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDOWByID(habits.getHabit_id());
        for(int i = 0; i < listHabitDow.size(); i++){
            Habit_DayOfWeek habit_dayOfWeek = listHabitDow.get(i);
            switch (habit_dayOfWeek.getHabit_DayOfWeek_id()){
                case 1: tgb2.setChecked(true);
                    break;
                case 2: tgb3.setChecked(true);
                    break;
                case 3: tgb4.setChecked(true);
                    break;
                case 4: tgb5.setChecked(true);
                    break;
                case 5: tgb6.setChecked(true);
                    break;
                case 6: tgb7.setChecked(true);
                    break;
                case 7: tgbCN.setChecked(true);
                    break;
            }
        }

        HabitsTitle.setText(habits.getTitle());
        HabitIcon.setImageResource(habits.getIcon());
        HabitTimePerDay.setText(String.valueOf(habits.getTimePerDay()));
        HabitColor.setBackgroundResource(habits.getColor());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setUpToggleButton() {
        tgb2 = findViewById(R.id.HabitsInfo_tgb2);
        tgb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb2.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb2.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb3 = findViewById(R.id.HabitsInfo_tgb3);
        tgb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb3.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb3.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb4 = findViewById(R.id.HabitsInfo_tgb4);
        tgb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb4.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb4.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb5 = findViewById(R.id.HabitsInfo_tgb5);
        tgb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb5.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb5.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb6 = findViewById(R.id.HabitsInfo_tgb6);
        tgb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb6.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb6.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgb7 = findViewById(R.id.HabitsInfo_tgb7);
        tgb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb7.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgb7.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
        tgbCN = findViewById(R.id.HabitsInfo_tgbCN);
        tgbCN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgbCN.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_on_shape));
                } else {
                    tgbCN.setBackgroundDrawable(getResources().getDrawable(R.drawable.toggle_button_off_shape));
                }
            }
        });
    }
}