package com.example.penguin_project.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.Constraints;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.CreateHabitActivity;
import com.example.penguin_project.HabitsInfoActivity;
import com.example.penguin_project.MainActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.data.ResetHabitsWorker;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.Habit_DayOfWeek;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.model.repo.local.Table.TimeOfDay;
import com.example.penguin_project.model.repo.local.Table.Tree;
import com.example.penguin_project.view.adapter.DatePicker_Adapter;
import com.example.penguin_project.view.adapter.HabitItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment {
    RecyclerView rcv_DatePicker, rcv_HabitListAnytime, rcv_HabitListMorning, rcv_HabitListAfternoon, rcv_HabitListEvening, rcv_HabitListCompleted, rcv_HabitListFailed;
    TextView txt_DayOfMonth, txt_DayOfWeek;
    TextView txt_Coin;
    ArrayList<HabitDate> arrDate;
    DatePicker_Adapter datePicker_adapter;
    List<Habits> habitsListAnytime, habitsListMorning, habitsListAfternoon, habitsListEvening, habitsListCompleted, habitsListFailed;
    HabitItemAdapter habitItemAnytimeAdapter, habitItemMorningAdapter, habitItemAfterNoonAdapter, habitItemEveningAdapter, habitItemCompletedAdapter, habitItemFailedAdapter;
    FloatingActionButton btn_AddHabit;
    DayOfWeek selectedDayOfWeek;

    public HomeFragment() {
        // Required empty public constructor
        //cmt
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        rcv_DatePicker = view.findViewById(R.id.rcv_datePicker);
        txt_DayOfWeek = view.findViewById(R.id.txt_ActionBar_DayOfWeek);
        txt_DayOfMonth = view.findViewById(R.id.txt_ActionBar_DayOfMonth);
        txt_Coin = view.findViewById(R.id.txt_ActionBar_coin);
        btn_AddHabit = view.findViewById(R.id.btn_Home_AddHabit);

        rcv_HabitListAnytime = view.findViewById(R.id.rcv_Home_AnytimeHabit);
        rcv_HabitListMorning = view.findViewById(R.id.rcv_Home_MorningHabit);
        rcv_HabitListAfternoon = view.findViewById(R.id.rcv_Home_AfternoonHabit);
        rcv_HabitListEvening = view.findViewById(R.id.rcv_Home_EveningHabit);
        rcv_HabitListCompleted = view.findViewById(R.id.rcv_Home_CompletedHabit);
        rcv_HabitListFailed = view.findViewById(R.id.rcv_Home_FailedHabit);

        MenuFragment.weekSettingsSP = view.getContext().getSharedPreferences("week_setting", Context.MODE_PRIVATE);


//        getContext().deleteDatabase("Habit.db");
//

        setTimeOfDay();
        setTreeForest();
//        setData();
        scheduleResetHabitsJob();

        selectedDayOfWeek = LocalDate.now().getDayOfWeek();

        Setting_DayOfWeekAndDayOfMonth();
        Setting_rcvDatePicker();
        Setting_rcvHabitAnytimeList();
        Setting_rcvHabitMorningList();
        Setting_rcvHabitAfternoonList();
        Setting_rcvHabitEveningList();
        Setting_rcvHabitConpletedList();
        Setting_rcvHabitFailedList();
        Setting_btnAddHabit();



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        MainActivity.getInstance().changeTheme();
        notifyDatabaseChange();
    }

    private void scheduleResetHabitsJob() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 1); // Thay đổi giờ và phút theo nhu cầu
        calendar.set(Calendar.MINUTE, 0);

// Kiểm tra nếu ngày hiện tại đã là thứ 2, thì cộng thêm 1 tuần
        if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
            calendar.add(Calendar.WEEK_OF_YEAR, 1);
        }

// Tính toán khoảng thời gian initialDelay
        long initialDelay = calendar.getTimeInMillis() - System.currentTimeMillis();

// Tạo công việc và đăng ký với WorkManager
        OneTimeWorkRequest resetHabitsRequest =
                new OneTimeWorkRequest.Builder(ResetHabitsWorker.class)
                        .setInitialDelay(initialDelay, TimeUnit.MILLISECONDS)
                        .build();

        WorkManager.getInstance(getContext()).enqueue(resetHabitsRequest);
    }

    private void setData() {
        HabitDataBase.getInstance(getContext());
        selectedDayOfWeek = LocalDate.now().getDayOfWeek();
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit(new Habits("Uong nuoc", 2, 5, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 0, 1));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit(new Habits("Doc Sach", 3, 2, R.color.purple_200, R.mipmap.icon_water, LocalDate.now().minusDays(1), 0, 0, 3));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit(new Habits("uong nuoc", 2, 5, R.color.purple_200, R.mipmap.icon_water, LocalDate.now().minusDays(2), 0, 0, 2));
//        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit(new Habits("Choi game", 2, 5, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 0, 1));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(selectedDayOfWeek.getValue(), 1, false, 0, false));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(selectedDayOfWeek.getValue(), 2, false, 0, false));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.TUESDAY.getValue(), 1, false, 0, false));
        HabitDataBase.getInstance(getContext()).habitDAO().insertHabit_DayOfWeek(new Habit_DayOfWeek(DayOfWeek.WEDNESDAY.getValue(), 4, false, 0, false));
    }


    // Hàm kiểm tra xem trong database có data trong table TimeOfDat hay không? Nếu không thì thêm?
    private void setTimeOfDay() {
        if (HabitDataBase.getInstance(getContext()).habitDAO().getTimeOfDayById(1) == null) {
            HabitDataBase.getInstance(getContext()).habitDAO().insert_TimeOfDay(new TimeOfDay(1, "Anytime"));
            HabitDataBase.getInstance(getContext()).habitDAO().insert_TimeOfDay(new TimeOfDay(2, "Morning"));
            HabitDataBase.getInstance(getContext()).habitDAO().insert_TimeOfDay(new TimeOfDay(3, "Afternoon"));
            HabitDataBase.getInstance(getContext()).habitDAO().insert_TimeOfDay(new TimeOfDay(4, "Evening"));
        }
    }

    // Hàm kiểm tra xem trong database có data trong table Tree hay không? nếu không thì thêm Tree Item vào
    private void setTreeForest() {
        if (HabitDataBase.getInstance(getContext()).habitDAO().getTreeForestById(1) == null) {
            Tree plant1 = new Tree("Tulip", R.mipmap.icon_storeiten_tulip, 2, false);
            Tree plant2 = new Tree("Maple", R.mipmap.icon_storeitem_maple_tree, 5, true);
            Tree plant3 = new Tree("Hickory", R.mipmap.icon_storeitem_hickory, 7, false);
            Tree plant4 = new Tree("Coconut", R.mipmap.icon_storeitem_coconuttree, 10, false);
            Tree plant5 = new Tree("Pine", R.mipmap.icon_pine, 5, false);
            Tree plant6 = new Tree("Bamboo", R.mipmap.icon_bamboo, 3, false);
            Toast.makeText(getContext(), "Not purchaed", Toast.LENGTH_LONG).show();
            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant2);
            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant1);

            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant3);
            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant4);
            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant5);
            HabitDataBase.getInstance(getContext()).habitDAO().insertTree(plant6);
        }

    }

    private void Setting_btnAddHabit() {
        btn_AddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CreateHabitActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Setting_rcvHabitFailedList() {
        habitsListFailed = new ArrayList<>();
        habitsListFailed = HabitDataBase.getInstance(getContext()).habitDAO().getFailedHabits(selectedDayOfWeek.getValue());

        habitItemFailedAdapter = new HabitItemAdapter(habitsListFailed, getContext(), LocalDate.now().getDayOfWeek());
        habitItemFailedAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListFailed.setLayoutManager(linearLayoutManager);
        rcv_HabitListFailed.setAdapter(habitItemFailedAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListFailed);
    }

    private void Setting_rcvHabitConpletedList() {
        habitsListCompleted = new ArrayList<>();
        habitsListCompleted = HabitDataBase.getInstance(getContext()).habitDAO().getDoneHabits(selectedDayOfWeek.getValue());

        habitItemCompletedAdapter = new HabitItemAdapter(habitsListCompleted, getContext(), LocalDate.now().getDayOfWeek());
        habitItemCompletedAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListCompleted.setLayoutManager(linearLayoutManager);
        rcv_HabitListCompleted.setAdapter(habitItemCompletedAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListCompleted);
    }

    private void Setting_rcvHabitEveningList() {
        habitsListEvening = new ArrayList<>();
        habitsListEvening = HabitDataBase.getInstance(getContext()).habitDAO().getEveningHabits(selectedDayOfWeek.getValue());

        habitItemEveningAdapter = new HabitItemAdapter(habitsListEvening, getContext(), LocalDate.now().getDayOfWeek());
        habitItemEveningAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListEvening.setLayoutManager(linearLayoutManager);
        rcv_HabitListEvening.setAdapter(habitItemEveningAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListEvening);
    }

    private void Setting_rcvHabitAfternoonList() {
        habitsListAfternoon = new ArrayList<>();
        habitsListAfternoon = HabitDataBase.getInstance(getContext()).habitDAO().getAfternoonHabits(selectedDayOfWeek.getValue());

        habitItemAfterNoonAdapter = new HabitItemAdapter(habitsListAfternoon, getContext(), LocalDate.now().getDayOfWeek());
        habitItemAfterNoonAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListAfternoon.setLayoutManager(linearLayoutManager);
        rcv_HabitListAfternoon.setAdapter(habitItemAfterNoonAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListAfternoon);
    }

    private void Setting_rcvHabitMorningList() {
        habitsListMorning = new ArrayList<>();
        habitsListMorning = HabitDataBase.getInstance(getContext()).habitDAO().getMorningHabits(selectedDayOfWeek.getValue());

        habitItemMorningAdapter = new HabitItemAdapter(habitsListMorning, getContext(), LocalDate.now().getDayOfWeek());
        habitItemMorningAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListMorning.setLayoutManager(linearLayoutManager);
        rcv_HabitListMorning.setAdapter(habitItemMorningAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListMorning);
    }

    private void Setting_rcvHabitAnytimeList() {
        habitsListAnytime = new ArrayList<>();
        habitsListAnytime = HabitDataBase.getInstance(getContext()).habitDAO().getAnytimeHabits(selectedDayOfWeek.getValue());

        habitItemAnytimeAdapter = new HabitItemAdapter(habitsListAnytime, getContext(), LocalDate.now().getDayOfWeek());
        habitItemAnytimeAdapter.setOnItemClickListener(new HabitItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Habits habit) {
                Intent intent = new Intent(getContext(), HabitsInfoActivity.class);
                intent.putExtra("habitKey", habit);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListAnytime.setLayoutManager(linearLayoutManager);
        rcv_HabitListAnytime.setAdapter(habitItemAnytimeAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListAnytime);
    }

    private void Setting_DayOfWeekAndDayOfMonth() {
        switch (LocalDate.now().getDayOfWeek()) {
            case MONDAY:
                txt_DayOfWeek.setText("Monday");
                break;
            case TUESDAY:
                txt_DayOfWeek.setText("Tuesday");
                break;
            case WEDNESDAY:
                txt_DayOfWeek.setText("Wednesday");
                break;
            case THURSDAY:
                txt_DayOfWeek.setText("Thursday");
                break;
            case FRIDAY:
                txt_DayOfWeek.setText("Friday");
                break;
            case SATURDAY:
                txt_DayOfWeek.setText("Saturday");
                break;
            case SUNDAY:
                txt_DayOfWeek.setText("Sunday");
                break;
        }
        txt_DayOfMonth.setText(String.valueOf(LocalDate.now().getDayOfMonth()));
    }


    private void Setting_rcvDatePicker() {
        arrDate = new ArrayList<>();
        LocalDate date = LocalDate.now();
        selectedDayOfWeek = date.getDayOfWeek();
        if (MenuFragment.weekSettingsSP == null) {
            Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
        }
        if ( MenuFragment.weekSettingsSP.getString("week_setting", "Monday").equals("Monday") ) {

            switch (LocalDate.now().getDayOfWeek()) {
                case MONDAY:
                    date = date.minusDays(0);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 0);
                    break;
                case TUESDAY:
                    date = date.minusDays(1);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 1);
                    break;
                case WEDNESDAY:
                    date = date.minusDays(2);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 2);
                    break;
                case THURSDAY:
                    date = date.minusDays(3);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 3);
                    break;
                case FRIDAY:
                    date = date.minusDays(4);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 4);
                    break;
                case SATURDAY:
                    date = date.minusDays(5);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 5);
                    break;
                case SUNDAY:
                    date = date.minusDays(6);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 6);
                    break;
            }
        }
        else {
            switch (LocalDate.now().getDayOfWeek()) {
                case MONDAY:
                    date = date.minusDays(1);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 0);
                    break;
                case TUESDAY:
                    date = date.minusDays(2);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 1);
                    break;
                case WEDNESDAY:
                    date = date.minusDays(3);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 2);
                    break;
                case THURSDAY:
                    date = date.minusDays(4);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 3);
                    break;
                case FRIDAY:
                    date = date.minusDays(5);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 4);
                    break;
                case SATURDAY:
                    date = date.minusDays(6);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 5);
                    break;
                case SUNDAY:
                    date = date.minusDays(7);
                    datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 6);
                    break;
            }
        }
        for (int i = 0; i < 7; i++) {
            arrDate.add(new HabitDate(date.plusDays(i)));
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_DatePicker.setLayoutManager(linearLayoutManager);


        // xử lý onClickItem trong datePicker
        datePicker_adapter.setOnItemClickListener(new DatePicker_Adapter.OnItemClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemClick(LocalDate date) {
                selectedDayOfWeek = date.getDayOfWeek();
                habitsListAnytime.clear();
                habitItemAnytimeAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListMorning.clear();
                habitItemMorningAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListAfternoon.clear();
                habitItemAfterNoonAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListEvening.clear();
                habitItemEveningAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListCompleted.clear();
                habitItemCompletedAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListFailed.clear();
                habitItemFailedAdapter.updateDayOfWeek(date.getDayOfWeek());

                notifyDatabaseChange();
            }
        });

        rcv_DatePicker.setAdapter(datePicker_adapter);
        rcv_DatePicker.scrollToPosition(LocalDate.now().getDayOfWeek().getValue() - 1);
    }



    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

        private Drawable deleteIcon;
        private Drawable checkDoneIcon;
        private final ColorDrawable deleteBackground;
        private final ColorDrawable checkDoneBackground;

        public SwipeToDeleteCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            deleteIcon = ContextCompat.getDrawable(getContext(), R.mipmap.icon_delete);
            checkDoneIcon = ContextCompat.getDrawable(getContext(), R.mipmap.icon_done);
            deleteBackground = new ColorDrawable(Color.RED);
            checkDoneBackground = new ColorDrawable(Color.GREEN);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            HabitItemAdapter.ItemViewHolder viewHolder1 = (HabitItemAdapter.ItemViewHolder) viewHolder;
            HabitItemAdapter habitItemAdapter = viewHolder1.getAdapter();
            if (direction == ItemTouchHelper.LEFT) {
                if (LocalDate.now().getDayOfWeek().getValue() >= selectedDayOfWeek.getValue()) {
                    habitItemAdapter.removeItem(position);
                    notifyDatabaseChange();
                }
                notifyDatabaseChange();
            } else if (direction == ItemTouchHelper.RIGHT) {
                if (LocalDate.now().getDayOfWeek().getValue() >= selectedDayOfWeek.getValue()) {
                    habitItemAdapter.checkDoneItem(position);
                    notifyDatabaseChange();
//                    Habits habits = HabitDataBase.getInstance(getContext()).habitDAO().get

                    Toast.makeText(getContext(), position + " done", Toast.LENGTH_SHORT).show();
                }
                notifyDatabaseChange();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            View itemView = viewHolder.itemView;
            int backgroundCornerOffset = 20;

            if (dX > 0) { // Swiping to the right (Check Done)
                int iconMargin = (itemView.getHeight() - checkDoneIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - checkDoneIcon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + checkDoneIcon.getIntrinsicHeight();

                int iconLeft = itemView.getLeft() + iconMargin;
                int iconRight = itemView.getLeft() + iconMargin + checkDoneIcon.getIntrinsicWidth();
                checkDoneIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                checkDoneBackground.setBounds(itemView.getLeft(), itemView.getTop(),
                        itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
            } else if (dX < 0) { // Swiping to the left (Delete)
                int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

                int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                int iconRight = itemView.getRight() - iconMargin;
                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                deleteBackground.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                        itemView.getTop(), itemView.getRight(), itemView.getBottom());
            } else { // View is unswiped
                deleteBackground.setBounds(0, 0, 0, 0);
                checkDoneBackground.setBounds(0, 0, 0, 0);
            }

            deleteBackground.draw(c);
            deleteIcon.draw(c);
            checkDoneBackground.draw(c);
            checkDoneIcon.draw(c);
        }
    }

    private void notifyDatabaseChange() {
        habitsListAnytime.clear();
        habitsListAnytime = HabitDataBase.getInstance(getContext()).habitDAO().getAnytimeHabits(selectedDayOfWeek.getValue());
        habitItemAnytimeAdapter.setHabits(habitsListAnytime);

        habitsListMorning.clear();
        habitsListMorning = HabitDataBase.getInstance(getContext()).habitDAO().getMorningHabits(selectedDayOfWeek.getValue());
        habitItemMorningAdapter.setHabits(habitsListMorning);

        habitsListAfternoon.clear();
        habitsListAfternoon = HabitDataBase.getInstance(getContext()).habitDAO().getAfternoonHabits(selectedDayOfWeek.getValue());
        habitItemAfterNoonAdapter.setHabits(habitsListAfternoon);

        habitsListEvening.clear();
        habitsListEvening = HabitDataBase.getInstance(getContext()).habitDAO().getEveningHabits(selectedDayOfWeek.getValue());
        habitItemEveningAdapter.setHabits(habitsListEvening);

        habitsListCompleted.clear();
        habitsListCompleted = HabitDataBase.getInstance(getContext()).habitDAO().getDoneHabits(selectedDayOfWeek.getValue());
        habitItemCompletedAdapter.setHabits(habitsListCompleted);

        habitsListFailed.clear();
        habitsListFailed = HabitDataBase.getInstance(getContext()).habitDAO().getFailedHabits(selectedDayOfWeek.getValue());
        habitItemFailedAdapter.setHabits(habitsListFailed);
    }
}