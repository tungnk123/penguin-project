package com.example.penguin_project.view.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.utils.NotificationBroadcastReceiver;
import com.example.penguin_project.viewmodel.TodoViewModel;

import java.util.Calendar;
import java.util.Date;

public class AddTodoActivity extends AppCompatActivity {

    public Button btnSave;
    public Button btnRemind;

    public TodoViewModel todoViewModel;
    public EditText etTodoName;
    public EditText etDesc;

    public Date notificationTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTodoName = findViewById(R.id.et_activityAddTodo_todoName);
        etDesc = findViewById(R.id.et_activityAddTodo_description);
        btnRemind = findViewById(R.id.btn_activityAddTodo_remindButton);

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        Toolbar toolbar = findViewById(R.id.tb_activityAddTodo_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnSave = findViewById(R.id.btn_activityAddTodo_saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoName = etTodoName.getText().toString();
                String description = etDesc.getText().toString();

                Todo todo = new Todo(todoName, description, false);
                todoViewModel.insertTodo(todo);
                setNotificationForDueTime(notificationTime);
                onBackPressed();

            }
        });

        btnRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateTimePicker();
            }
        });
    }

    private void openDateTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(AddTodoActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        final int selectedYear = year;
                        final int selectedMonth = month;
                        final int selectedDayOfMonth = dayOfMonth;

                        TimePickerDialog timePickerDialog = new TimePickerDialog(AddTodoActivity.this,
                                new TimePickerDialog.OnTimeSetListener() {
                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                        final int selectedHourOfDay = hourOfDay;
                                        final int selectedMinute = minute;

                                        Calendar dueTimeCalendar = Calendar.getInstance();
                                        dueTimeCalendar.set(selectedYear, selectedMonth, selectedDayOfMonth, selectedHourOfDay, selectedMinute);
                                        Date dueTime = dueTimeCalendar.getTime();
                                        notificationTime = dueTimeCalendar.getTime();
//                                        setNotificationForDueTime(dueTime);
                                    }
                                }, hour, minute, false);
                        timePickerDialog.show();
                    }
                }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }

    private void setNotificationForDueTime(Date dueTime) {
        long dueTimeMillis = dueTime.getTime();

        try {
            Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
            intent.putExtra("todoName", etTodoName.getText().toString());
            intent.putExtra("description", "Your task is due! Please go and do it! ❤❤❤❤❤❤❤❤😊😊😊😊😊");
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager.cancel(alarmPendingIntent);
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, dueTimeMillis - 20000, alarmPendingIntent);
            }

            Toast.makeText(AddTodoActivity.this, "Notification set for " + dueTime.toString(), Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(AddTodoActivity.this, "Notification permission denied", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
