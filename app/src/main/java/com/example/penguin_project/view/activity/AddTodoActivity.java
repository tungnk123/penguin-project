package com.example.penguin_project.view.activity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.utils.NotificationBroadcastReceiver;
import com.example.penguin_project.view.adapter.CustomSpinnerAdapter;
import com.example.penguin_project.viewmodel.TodoViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddTodoActivity extends AppCompatActivity {

    public Button btnSave;
    public Button btnRemind;

    public Button btnDueTime;
    public TodoViewModel todoViewModel;
    public EditText etTodoName;
    public EditText etDesc;

    public Date notificationTime;

    public Date dueTimePick;


    public boolean isRepeating = false;
    public String repeatType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        etTodoName = findViewById(R.id.et_activityAddTodo_todoName);
        etDesc = findViewById(R.id.et_activityAddTodo_description);
        btnRemind = findViewById(R.id.btn_activityAddTodo_remindButton);
        btnDueTime = findViewById(R.id.btn_activityAddTodo_ChooseButton);
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

                if (notificationTime != null) {
                    setNotification(notificationTime, todoName, "Remind task ! ❤❤😊😂😁💕🤞🤞😊😂😁💕🤞🤞", "reminder");
                }

                if (dueTimePick != null) {
                    setNotification(dueTimePick, todoName, "Your task is due! Please go and do it! ❤❤❤❤❤❤❤❤😊😊😊😊😊", "dueTime");
                }


                onBackPressed();
            }
        });


        btnRemind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateTimePicker(1);

            }
        });

        btnDueTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDateTimePicker(2);

            }
        });

        Spinner spinner = findViewById(R.id.spner_activityAddTodo_repeatSpinner);
        CharSequence[] options = getResources().getTextArray(R.array.spinner_options);
        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, R.layout.custom_spinner_item, options);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Handle the selected item
                // TODO: xu ly phan repeat tai day
                String selectedOption = options[position].toString();

                switch (selectedOption) {
                    case "Every day":
                        //
                        setRepeatDalyNotification();
                        break;
                    case "Every week":
                        setRepeatWeekNotification();
                        break;
                    case "Every month":
                        setRepeatMonthNotification();
                        break;
                    case "Every year":
                        setRepeatYearNotification();
                        break;

                    default:
                        Toast.makeText(AddTodoActivity.this, "Not repeaat", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle the case when nothing is selected
            }
        });

    }


    private void openDateTimePicker(int number) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        final Date[] dueTime = new Date[1]; // Array to hold the dueTime value

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
                                        Date dueTime = dueTimeCalendar.getTime(); // Store the dueTime value
                                        if (number == 1) {
                                            notificationTime = dueTime;
                                            SimpleDateFormat formatter
                                                    = new SimpleDateFormat("hh:mm dd.MM.yyyy ");
                                            String btnText = "Remind time at " + formatter.format(notificationTime);
                                            btnRemind.setText(btnText);
                                        } else if (number == 2) {
                                            dueTimePick = dueTime;
                                            SimpleDateFormat formatter
                                                    = new SimpleDateFormat("dd.MM.yyyy ");
                                            String btnText = "Due date:" +  formatter.format(dueTimePick);
                                            btnDueTime.setText(btnText);
                                        }
//                                  setNotificationForDueTime(dueTime);
                                    }
                                }, hour, minute, false);
                        timePickerDialog.show();
                    }
                }, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();

    }


    private void setNotification(Date time, String title, String description, String notificationType) {
        long notificationTimeMillis = time.getTime();

        try {
            Intent intent = new Intent(this, NotificationBroadcastReceiver.class);
            if (notificationType.equals("reminder")) {
                intent.putExtra("todoName", "Reminder: " + title);
                intent.putExtra("type", "reminder");
            } else if (notificationType.equals("dueTime")) {
                intent.putExtra("todoName", "Due Time: " + title);
                intent.putExtra("type", "dueTime");
            }
            int requestCode;
            if (notificationType.equals("reminder")) {
                requestCode = 0;
            } else {
                requestCode = 1;
            }
            intent.putExtra("description", description);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            if (alarmManager != null) {
                PendingIntent alarmPendingIntent = PendingIntent.getBroadcast(this, requestCode, intent, PendingIntent.FLAG_IMMUTABLE);
                alarmManager.cancel(alarmPendingIntent);
                if (!isRepeating) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, notificationTimeMillis - 20000, alarmPendingIntent);
                } else {
                    switch (repeatType) {
                        case "Every day":
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notificationTimeMillis - 20000, AlarmManager.INTERVAL_DAY, alarmPendingIntent);
                            break;
                        case "Every week":
                            Calendar nextWeek = Calendar.getInstance();
                            nextWeek.setTimeInMillis(notificationTimeMillis);
                            nextWeek.add(Calendar.WEEK_OF_YEAR, 1);
                            long nextWeekMillis = nextWeek.getTimeInMillis();
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nextWeekMillis - 20000, AlarmManager.INTERVAL_DAY * 7, alarmPendingIntent);
                            break;
                        case "Every month":
                            Calendar nextMonth = Calendar.getInstance();
                            nextMonth.setTimeInMillis(notificationTimeMillis);
                            nextMonth.add(Calendar.MONTH, 1);
                            long nextMonthMillis = nextMonth.getTimeInMillis();
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nextMonthMillis - 20000, AlarmManager.INTERVAL_DAY * 30, alarmPendingIntent);
                            break;
                        case "Every year":
                            Calendar nextYear = Calendar.getInstance();
                            nextYear.setTimeInMillis(notificationTimeMillis);
                            nextYear.add(Calendar.YEAR, 1);
                            long nextYearMillis = nextYear.getTimeInMillis();
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, nextYearMillis, AlarmManager.INTERVAL_DAY * 365, alarmPendingIntent);
                            break;

                    }
                }

            }

            Toast.makeText(AddTodoActivity.this, "Notification set for " + time.toString(), Toast.LENGTH_LONG).show();
        } catch (SecurityException e) {
            Toast.makeText(AddTodoActivity.this, "Notification permission denied", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


    private void setRepeatDalyNotification() {
        isRepeating = true;
        repeatType = "Every day";
    }

    private void setRepeatWeekNotification() {
        isRepeating = true;
        repeatType = "Every week";
    }

    private void setRepeatMonthNotification() {
        isRepeating = true;
        repeatType = "Every month";
    }

    private void setRepeatYearNotification() {
        isRepeating = true;
        repeatType = "Every year";
    }

}
