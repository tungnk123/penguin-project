package com.example.penguin_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HabitsInfoActivity extends AppCompatActivity {

    ToggleButton tgb2, tgb3, tgb4, tgb5, tgb6, tgb7, tgbCN;
    LinearLayout btn_anytime, btn_morning, btn_afternoon, btn_evening;
    TextView HabitsTitle, HabitTimePerDay, HabitDays;
    RelativeLayout HabitColor;
    ImageView HabitIcon;
    ImageButton btn_back, btn_deleteHabit, btn_editHabit;
    Habits selectedHabit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habits_info);

        setUpToggleButton();
        setttingControls();
        Loadhabits();
        setUp3Button();

    }

    private void setUp3Button() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HabitsInfoActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa thói quen này");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Thực hiện xóa bản ghi
                        List<Habit_DayOfWeek> listHabitDow = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDOWByID(selectedHabit.getHabit_id());
                        for(int i = 0; i < listHabitDow.size(); i++){
                            Habit_DayOfWeek habit_dayOfWeek = listHabitDow.get(i);
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().deleteHabitDayOfWeek(habit_dayOfWeek);
                        }
                        HabitDataBase.getInstance(getApplicationContext()).habitDAO().deleteHabit(selectedHabit);
                        finish();
                    }
                });
                builder.setNegativeButton("Hủy", null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void Loadhabits() {
        Intent intent = getIntent();
        selectedHabit = (Habits)intent.getSerializableExtra("habitKey");
        switch (selectedHabit.getTimeOfDay_id()){
            case 1: btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
                break;
            case 2: btn_morning.setBackgroundResource(R.drawable.item_timeofday_shape);
                break;
            case 3: btn_afternoon.setBackgroundResource(R.drawable.item_timeofday_shape);
                break;
            case 4: btn_evening.setBackgroundResource(R.drawable.item_timeofday_shape);
                break;
        }

        List<Habit_DayOfWeek> listHabitDow = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDOWByID(selectedHabit.getHabit_id());
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

        HabitsTitle.setText(selectedHabit.getTitle());
        HabitIcon.setImageResource(selectedHabit.getIcon());
        HabitTimePerDay.setText(String.valueOf(selectedHabit.getTimePerDay()));
        HabitColor.setBackgroundResource(selectedHabit.getColor());
        HabitDays.setText(String.valueOf(ChronoUnit.DAYS.between(selectedHabit.getCreateDay(), LocalDate.now())) + " DAY");

    }

    private void setttingControls() {
        btn_anytime = findViewById(R.id.Habits_info_btnAnytime);
        btn_morning = findViewById(R.id.Habits_info_btnMorning);
        btn_afternoon = findViewById(R.id.Habits_info_btnAfternoon);
        btn_evening = findViewById(R.id.Habits_info_btnEvening);

        HabitsTitle = findViewById(R.id.HabitInfo_habitItem_Title);
        HabitTimePerDay = findViewById(R.id.Habits_info_timePerDay);
        HabitColor = findViewById(R.id.HabitInfo_habitItem_Color);
        HabitIcon = findViewById(R.id.HabitInfo_habitItem_Icon);
        HabitDays = findViewById(R.id.txt_HabitInfoDay);

        btn_back = findViewById(R.id.HabitsInfo_btn_back);
        btn_deleteHabit = findViewById(R.id.HabitsInfo_btn_deleteHabits);
        btn_editHabit = findViewById(R.id.HabitsInfo_btn_editHabits);
    }

    private void setUpToggleButton() {
        tgb2 = findViewById(R.id.HabitsInfo_tgb2);
        tgb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb2.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb2.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb3 = findViewById(R.id.HabitsInfo_tgb3);
        tgb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb3.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb3.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb4 = findViewById(R.id.HabitsInfo_tgb4);
        tgb4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb4.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb4.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb5 = findViewById(R.id.HabitsInfo_tgb5);
        tgb5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb5.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb5.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb6 = findViewById(R.id.HabitsInfo_tgb6);
        tgb6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb6.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb6.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgb7 = findViewById(R.id.HabitsInfo_tgb7);
        tgb7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgb7.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgb7.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
        tgbCN = findViewById(R.id.HabitsInfo_tgbCN);
        tgbCN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tgbCN.setBackgroundResource(R.drawable.toggle_button_on_shape);
                } else {
                    tgbCN.setBackgroundResource(R.drawable.toggle_button_off_shape);
                }
            }
        });
    }
}