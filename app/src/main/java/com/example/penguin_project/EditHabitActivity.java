package com.example.penguin_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.penguin_project.model.data.LocalDateTimeConverter;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.Tree;
import com.example.penguin_project.view.adapter.PlantAdapter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EditHabitActivity extends AppCompatActivity {

    TextView mainTitle, addTitle;
    EditText edt_habitName, edt_habitTimePerDay;
    ImageButton btn_color1, btn_color2, btn_color3, btn_color4, btn_color5, btn_color6, btn_color7, btn_color8, btn_iconPicker, btn_back;
    ImageView habitItemIcon;
    RelativeLayout habitItemColor, btn_addHabit;
    ToggleButton tgb2, tgb3, tgb4, tgb5, tgb6, tgb7, tgbCN;
    LinearLayout btn_anytime, btn_morning, btn_afternoon, btn_evening;
    Button btn_EditHour;
    CheckBox cb_RemindTime;
    int imgResource;
    boolean isEdit = false;
    Habits editHabit;
    int Hour, Minutes;

    // Plant

    public RecyclerView plantRecyclerView;
    public List<Tree> plantList;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_habit);

        Hour = 20;
        Minutes = 0;

        settingToggleButton();
        settingTimeOfDay();
        settingColorButton();
        settingOtherControls();
        settingIconPicker();
        imgResource = R.mipmap.icon_drink_water;
        settingAddHabit();
        settingRemindTime();
        receiveIntent();

        //region Plant
        plantRecyclerView = findViewById(R.id.rcv_EditHabit_plantRecyclerView);
//        plantList = new ArrayList<>();
//
//        Tree plant1 = new Tree("Plant 1", R.mipmap.icon_oak_tree, 5);
//        Tree plant2 = new Tree("Plant 2", R.mipmap.icon_bamboo, 5);
//        Tree plant3 = new Tree("Plant 3", R.mipmap.icon_bonsai_4, 5);
//
//        plantList.add(plant1);
//        plantList.add(plant2);
//        plantList.add(plant3);
        plantList = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getTreeForestList();
        PlantAdapter plantAdapter = new PlantAdapter(plantList);

        plantRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        plantRecyclerView.setAdapter(plantAdapter);
        //endregion
    }

    private void settingRemindTime() {
        cb_RemindTime = findViewById(R.id.EditHabit_cbRemindTime);
        btn_EditHour = findViewById(R.id.EditHabit_btnEditHour);

        btn_EditHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        Hour = selectedHour;
                        Minutes = selectedMinute;
                        btn_EditHour.setText(String.format(Locale.getDefault(), "%02d:%02d", Hour, Minutes));
                    }
                };
                int style = AlertDialog.THEME_HOLO_DARK;
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditHabitActivity.this, style, onTimeSetListener, Hour, Minutes, true);
                timePickerDialog.setTitle("Select time");
                timePickerDialog.show();
            }
        });
    }

    private void settingAddHabit() {
        btn_addHabit = findViewById(R.id.EditHabit_btn_addHabit);
        btn_addHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    editHabit.setColor((int) habitItemColor.getTag());
                    editHabit.setIcon(imgResource);
                    editHabit.setTitle(String.valueOf(edt_habitName.getText()));
                    if ((int) btn_anytime.getTag() == R.drawable.item_timeofday_shape) {
                        editHabit.setTimeOfDay_id(1);
                    }
                    if ((int) btn_morning.getTag() == R.drawable.item_timeofday_shape) {
                        editHabit.setTimeOfDay_id(2);
                    }
                    if ((int) btn_afternoon.getTag() == R.drawable.item_timeofday_shape) {
                        editHabit.setTimeOfDay_id(3);
                    }
                    if ((int) btn_evening.getTag() == R.drawable.item_timeofday_shape) {
                        editHabit.setTimeOfDay_id(4);
                    }
                    int choosingDay = 0;
                    if (tgb2.isChecked()) choosingDay++;
                    if (tgb3.isChecked()) choosingDay++;
                    if (tgb4.isChecked()) choosingDay++;
                    if (tgb5.isChecked()) choosingDay++;
                    if (tgb6.isChecked()) choosingDay++;
                    if (tgb7.isChecked()) choosingDay++;
                    if (tgbCN.isChecked()) choosingDay++;

                    if (choosingDay == 0) {
                        Toast.makeText(getApplicationContext(), "choose at least 1 day of the week", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        List<Habit_DayOfWeek> habit_dayOfWeeks = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDOWByID(editHabit.getHabit_id());
                        for (Habit_DayOfWeek habit_dayOfWeek : habit_dayOfWeeks) {
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().deleteHabitDayOfWeek(habit_dayOfWeek);
                        }
                        if (tgb2.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.MONDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgb3.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.TUESDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgb4.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.WEDNESDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgb5.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.THURSDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgb6.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.FRIDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgb7.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SATURDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                        if (tgbCN.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SUNDAY.getValue(), editHabit.getHabit_id(), false, 0, false));
                    }
                    if (Integer.parseInt(String.valueOf(edt_habitTimePerDay.getText())) < 1) {
                        Toast.makeText(getApplicationContext(), "Time per day must greater than 0", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        editHabit.setTimePerDay(Integer.parseInt(String.valueOf(edt_habitTimePerDay.getText())));
                    }
                    if(cb_RemindTime.isChecked()){
                        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), Hour, Minutes);
                        editHabit.setHourOfDay(localDateTime);
                    }
                    else {
                        editHabit.setHourOfDay(null);
                    }
                    HabitDataBase.getInstance(getApplicationContext()).habitDAO().updateHabits(editHabit);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                } else {
                    String title = String.valueOf(edt_habitName.getText());
                    int timePerDay = Integer.parseInt(String.valueOf(edt_habitTimePerDay.getText()));
                    int timeOfDayID = 1;
                    if ((int) btn_anytime.getTag() == R.drawable.item_timeofday_shape) {
                        timeOfDayID = 1;
                    }
                    if ((int) btn_morning.getTag() == R.drawable.item_timeofday_shape) {
                        timeOfDayID = 2;
                    }
                    if ((int) btn_afternoon.getTag() == R.drawable.item_timeofday_shape) {
                        timeOfDayID = 3;
                    }
                    if ((int) btn_evening.getTag() == R.drawable.item_timeofday_shape) {
                        timeOfDayID = 4;
                    }
                    int color = (int) habitItemColor.getTag();
                    int img = imgResource;

                    int choosingDay = 0;
                    if (tgb2.isChecked()) choosingDay++;
                    if (tgb3.isChecked()) choosingDay++;
                    if (tgb4.isChecked()) choosingDay++;
                    if (tgb5.isChecked()) choosingDay++;
                    if (tgb6.isChecked()) choosingDay++;
                    if (tgb7.isChecked()) choosingDay++;
                    if (tgbCN.isChecked()) choosingDay++;

                    if (choosingDay == 0) {
                        Toast.makeText(getApplicationContext(), "choose at least 1 day of the week", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(cb_RemindTime.isChecked()){
                        LocalDateTime localDateTime = LocalDateTime.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), LocalDate.now().getDayOfMonth(), Hour, Minutes);
                        Habits habits = new Habits(title, timeOfDayID, timePerDay, color, img, LocalDate.now(), 0, 0, PlantAdapter.selectedPosition + 1, localDateTime);
                        HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit(habits);
                        List<Habits> ListHabit = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getHabitList();
                        Habits recentHabit = ListHabit.get(ListHabit.size() - 1);
                        if (tgb2.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.MONDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb3.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.TUESDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb4.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.WEDNESDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb5.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.THURSDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb6.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.FRIDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb7.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SATURDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgbCN.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SUNDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Habits habits = new Habits(title, timeOfDayID, timePerDay, color, img, LocalDate.now(), 0, 0, PlantAdapter.selectedPosition + 1, null);
                        HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit(habits);
                        List<Habits> ListHabit = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getHabitList();
                        Habits recentHabit = ListHabit.get(ListHabit.size() - 1);
                        if (tgb2.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.MONDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb3.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.TUESDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb4.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.WEDNESDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb5.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.THURSDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb6.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.FRIDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgb7.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SATURDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));
                        if (tgbCN.isChecked())
                            HabitDataBase.getInstance(getApplicationContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.SUNDAY.getValue(), recentHabit.getHabit_id(), false, 0, false));

                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void receiveIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            String action = intent.getAction();
            if (action.equals("New habit")) {
                imgResource = intent.getIntExtra("Icon", 0);
                habitItemIcon.setImageResource(imgResource);
                String Title = intent.getStringExtra("Title");
                edt_habitName.setText(Title);
            } else if (action.equals("Blank habit")) {
                return;
            } else if (action.equals("Selected habit")) {
                isEdit = true;
                Habits habits = (Habits) intent.getSerializableExtra("Selected habit");
                editHabit = habits;
                imgResource = habits.getIcon();
                mainTitle.setText("Edit habit");
                addTitle.setText("Edit");

                if(habits.getHourOfDay() != null){
                    Hour = habits.getHourOfDay().getHour();
                    Minutes = habits.getHourOfDay().getMinute();
                    cb_RemindTime.setChecked(true);
                    btn_EditHour.setText(String.format(Locale.getDefault(), "%02d:%02d", Hour, Minutes));
                }

                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);

                switch (habits.getTimeOfDay_id()) {
                    case 1:
                        btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
                        break;
                    case 2:
                        btn_morning.setBackgroundResource(R.drawable.item_timeofday_shape);
                        break;
                    case 3:
                        btn_afternoon.setBackgroundResource(R.drawable.item_timeofday_shape);
                        break;
                    case 4:
                        btn_evening.setBackgroundResource(R.drawable.item_timeofday_shape);
                        break;
                }

                List<Habit_DayOfWeek> listHabitDow = HabitDataBase.getInstance(getApplicationContext()).habitDAO().getListHabitDOWByID(habits.getHabit_id());
                for (int i = 0; i < listHabitDow.size(); i++) {
                    Habit_DayOfWeek habit_dayOfWeek = listHabitDow.get(i);
                    switch (habit_dayOfWeek.getHabit_DayOfWeek_id()) {
                        case 1:
                            tgb2.setChecked(true);
                            break;
                        case 2:
                            tgb3.setChecked(true);
                            break;
                        case 3:
                            tgb4.setChecked(true);
                            break;
                        case 4:
                            tgb5.setChecked(true);
                            break;
                        case 5:
                            tgb6.setChecked(true);
                            break;
                        case 6:
                            tgb7.setChecked(true);
                            break;
                        case 7:
                            tgbCN.setChecked(true);
                            break;
                    }
                }

                edt_habitName.setText(habits.getTitle());
                habitItemIcon.setImageResource(habits.getIcon());
                edt_habitTimePerDay.setText(String.valueOf(habits.getTimePerDay()));
                habitItemColor.setBackgroundResource(habits.getColor());
                habitItemColor.setTag(habits.getColor());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // Xử lý kết quả trả về
                if (data != null) {
                    // Lấy dữ liệu kết quả từ Intent
                    int result = data.getIntExtra("Icon", 0);
                    habitItemIcon.setImageResource(result);
                    imgResource = result;
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // Xử lý khi người dùng hủy bỏ hoặc không có kết quả trả về
            }
        }
    }

    private void settingIconPicker() {
        btn_iconPicker = findViewById(R.id.EditHabit_btn_iconPicker);
        btn_iconPicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), IconPickerActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void settingOtherControls() {
        addTitle = findViewById(R.id.EditHabit_AddTitle);
        mainTitle = findViewById(R.id.EditHabit_mainTitle);
        edt_habitName = findViewById(R.id.EdtiHabit_edtHabitName);
        edt_habitTimePerDay = findViewById(R.id.EditHabit_edtTimePerDay);
        habitItemIcon = findViewById(R.id.EditHabit_habitItem_Icon);
        btn_back = findViewById(R.id.EditHabit_btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void settingColorButton() {
        habitItemColor = findViewById(R.id.EditHabit_habitItem_Color);
        habitItemColor.setTag(R.drawable.item_icon_shape);
        btn_color1 = findViewById(R.id.EditHabit_btnColor1);
        btn_color2 = findViewById(R.id.EditHabit_btnColor2);
        btn_color3 = findViewById(R.id.EditHabit_btnColor3);
        btn_color4 = findViewById(R.id.EditHabit_btnColor4);
        btn_color5 = findViewById(R.id.EditHabit_btnColor5);
        btn_color6 = findViewById(R.id.EditHabit_btnColor6);
        btn_color7 = findViewById(R.id.EditHabit_btnColor7);
        btn_color8 = findViewById(R.id.EditHabit_btnColor8);

        btn_color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape);
                habitItemColor.setTag(R.drawable.item_circle_shape);
            }
        });
        btn_color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_2);
                habitItemColor.setTag(R.drawable.item_circle_shape_2);
            }
        });
        btn_color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_3);
                habitItemColor.setTag(R.drawable.item_circle_shape_3);
            }
        });
        btn_color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_4);
                habitItemColor.setTag(R.drawable.item_circle_shape_4);
            }
        });
        btn_color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_5);
                habitItemColor.setTag(R.drawable.item_circle_shape_5);
            }
        });
        btn_color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_6);
                habitItemColor.setTag(R.drawable.item_circle_shape_6);
            }
        });
        btn_color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_7);
                habitItemColor.setTag(R.drawable.item_circle_shape_7);
            }
        });
        btn_color8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitItemColor.setBackgroundResource(R.drawable.item_circle_shape_8);
                habitItemColor.setTag(R.drawable.item_circle_shape_8);
            }
        });
    }

    private void settingTimeOfDay() {
        btn_anytime = findViewById(R.id.EditHabit_btnAnytime);
        btn_morning = findViewById(R.id.EditHabit_btnMorning);
        btn_afternoon = findViewById(R.id.EditHabit_btnAfternoon);
        btn_evening = findViewById(R.id.EditHabit_btnEvening);

        btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
        btn_anytime.setTag(R.drawable.item_timeofday_shape);
        btn_morning.setTag(R.drawable.item_shape);
        btn_afternoon.setTag(R.drawable.item_shape);
        btn_evening.setTag(R.drawable.item_shape);

        btn_anytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_anytime.setTag(R.drawable.item_timeofday_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setTag(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setTag(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setTag(R.drawable.item_shape);
            }
        });
        btn_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_anytime.setTag(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_morning.setTag(R.drawable.item_timeofday_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setTag(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setTag(R.drawable.item_shape);
            }
        });
        btn_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_anytime.setTag(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setTag(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_afternoon.setTag(R.drawable.item_timeofday_shape);
                btn_evening.setBackgroundResource(R.drawable.item_shape);
                btn_evening.setTag(R.drawable.item_shape);
            }
        });
        btn_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_anytime.setBackgroundResource(R.drawable.item_shape);
                btn_anytime.setTag(R.drawable.item_shape);
                btn_morning.setBackgroundResource(R.drawable.item_shape);
                btn_morning.setTag(R.drawable.item_shape);
                btn_afternoon.setBackgroundResource(R.drawable.item_shape);
                btn_afternoon.setTag(R.drawable.item_shape);
                btn_evening.setBackgroundResource(R.drawable.item_timeofday_shape);
                btn_evening.setTag(R.drawable.item_timeofday_shape);
            }
        });

    }

    private void settingToggleButton() {
        tgb2 = findViewById(R.id.EditHabit_tgb2);
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
        tgb3 = findViewById(R.id.EditHabit_tgb3);
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
        tgb4 = findViewById(R.id.EditHabit_tgb4);
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
        tgb5 = findViewById(R.id.EditHabit_tgb5);
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
        tgb6 = findViewById(R.id.EditHabit_tgb6);
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
        tgb7 = findViewById(R.id.EditHabit_tgb7);
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
        tgbCN = findViewById(R.id.EditHabit_tgbCN);
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