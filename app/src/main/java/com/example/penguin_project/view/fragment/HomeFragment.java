package com.example.penguin_project.view.fragment;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;
import com.example.penguin_project.model.repo.local.DataBase.HabitDataBase;
import com.example.penguin_project.model.repo.local.Table.HabitGroup;
import com.example.penguin_project.model.repo.local.Table.Habits;
import com.example.penguin_project.view.adapter.DatePicker_Adapter;
import com.example.penguin_project.view.adapter.HabitItemAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView rcv_DatePicker, rcv_HabitListAnytime, rcv_HabitListMorning, rcv_HabitListAfternoon, rcv_HabitListEvening, rcv_HabitListCompleted, rcv_HabitListFailed;
    TextView txt_DayOfMonth, txt_DayOfWeek;
    TextView txt_Coin;
    ArrayList<HabitDate> arrDate;
    DatePicker_Adapter datePicker_adapter;
    List<Habits> habitsListAnytime, habitsListMorning, habitsListAfternoon, habitsListEvening, habitsListCompleted, habitsListFailed;
    HabitItemAdapter habitItemAnytimeAdapter, habitItemMorningAdapter, habitItemAfterNoonAdapter, habitItemEveningAdapter, habitItemCompletedAdapter, habitItemFailedAdapter;
    FloatingActionButton btn_AddHabit;


    public HomeFragment() {
        // Required empty public constructor
        //cmt
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        rcv_DatePicker = view.findViewById(R.id.rcv_datePicker);
        txt_DayOfWeek = view.findViewById(R.id.txt_ActionBar_DayOfWeek);
        txt_DayOfMonth = view.findViewById(R.id.txt_ActionBar_DayOfMonth);
        txt_Coin = view.findViewById(R.id.txt_ActionBar_coin);
        btn_AddHabit = view.findViewById(R.id.btn_Home_AddHabit);

        rcv_HabitListAnytime = view.findViewById(R.id.rcv_Home_AnytimeHabit);
        rcv_HabitListAnytime.setTag("rcv_HabitListAnytime");
        rcv_HabitListMorning = view.findViewById(R.id.rcv_Home_MorningHabit);
        rcv_HabitListMorning.setTag("rcv_HabitListMorning");
        rcv_HabitListAfternoon = view.findViewById(R.id.rcv_Home_AfternoonHabit);
        rcv_HabitListAfternoon.setTag("rcv_HabitListAfternoon");
        rcv_HabitListEvening = view.findViewById(R.id.rcv_Home_EveningHabit);
        rcv_HabitListEvening.setTag("rcv_HabitListEvening");
        rcv_HabitListCompleted = view.findViewById(R.id.rcv_Home_CompletedHabit);
        rcv_HabitListCompleted.setTag("rcv_HabitListCompleted");
        rcv_HabitListFailed = view.findViewById(R.id.rcv_Home_FailedHabit);
        rcv_HabitListFailed.setTag("rcv_HabitListFailed");

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

    private void Setting_btnAddHabit() {
        btn_AddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Setting_rcvHabitFailedList() {
        habitsListFailed = new ArrayList<>();
        habitsListFailed.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListFailed.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListFailed.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemFailedAdapter = new HabitItemAdapter(habitsListFailed, getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListFailed.setLayoutManager(linearLayoutManager);
        rcv_HabitListFailed.setAdapter(habitItemFailedAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListFailed);
    }

    private void Setting_rcvHabitConpletedList() {
        habitsListCompleted = new ArrayList<>();
        habitsListCompleted.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListCompleted.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListCompleted.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemCompletedAdapter = new HabitItemAdapter(habitsListCompleted,getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListCompleted.setLayoutManager(linearLayoutManager);
        rcv_HabitListCompleted.setAdapter(habitItemCompletedAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListCompleted);
    }

    private void Setting_rcvHabitEveningList() {
        habitsListEvening = new ArrayList<>();
        habitsListEvening.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListEvening.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListEvening.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemEveningAdapter = new HabitItemAdapter(habitsListEvening,getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListEvening.setLayoutManager(linearLayoutManager);
        rcv_HabitListEvening.setAdapter(habitItemEveningAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListEvening);
    }

    private void Setting_rcvHabitAfternoonList() {
        habitsListAfternoon = new ArrayList<>();
        habitsListAfternoon.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListAfternoon.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListAfternoon.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemAfterNoonAdapter = new HabitItemAdapter(habitsListAfternoon,getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListAfternoon.setLayoutManager(linearLayoutManager);
        rcv_HabitListAfternoon.setAdapter(habitItemAfterNoonAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListAfternoon);
    }

    private void Setting_rcvHabitMorningList() {
        habitsListMorning = new ArrayList<>();
        habitsListMorning.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListMorning.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListMorning.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemMorningAdapter = new HabitItemAdapter(habitsListMorning,getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListMorning.setLayoutManager(linearLayoutManager);
        rcv_HabitListMorning.setAdapter(habitItemMorningAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListMorning);
    }

    private void Setting_rcvHabitAnytimeList() {
        habitsListAnytime = new ArrayList<>();
        habitsListAnytime.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.drawable.item_icon_shape, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListAnytime.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsListAnytime.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemAnytimeAdapter = new HabitItemAdapter(habitsListAnytime,getContext(), LocalDate.now().getDayOfWeek());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcv_HabitListAnytime.setLayoutManager(linearLayoutManager);
        rcv_HabitListAnytime.setAdapter(habitItemAnytimeAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_HabitListAnytime);
    }

    private void Setting_DayOfWeekAndDayOfMonth() {
        switch (LocalDate.now().getDayOfWeek()){
            case MONDAY: txt_DayOfWeek.setText("Monday");
                break;
            case TUESDAY: txt_DayOfWeek.setText("Tuesday");
                break;
            case WEDNESDAY: txt_DayOfWeek.setText("Wednesday");
                break;
            case THURSDAY: txt_DayOfWeek.setText("Thursday");
                break;
            case FRIDAY: txt_DayOfWeek.setText("Friday");
                break;
            case SATURDAY: txt_DayOfWeek.setText("Saturday");
                break;
            case SUNDAY: txt_DayOfWeek.setText("Sunday");
                break;
        }
        txt_DayOfMonth.setText(String.valueOf(LocalDate.now().getDayOfMonth()));
    }



    private void Setting_rcvDatePicker() {
        arrDate = new ArrayList<>();
        LocalDate date = LocalDate.now();
        switch (LocalDate.now().getDayOfWeek()){
            case MONDAY: date = date.minusDays(0);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 0);
                break;
            case TUESDAY: date = date.minusDays(1);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 1);
                break;
            case WEDNESDAY: date = date.minusDays(2);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 2);
                break;
            case THURSDAY: date = date.minusDays(3);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 3);
                break;
            case FRIDAY: date = date.minusDays(4);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 4);
                break;
            case SATURDAY: date = date.minusDays(5);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 5);
                break;
            case SUNDAY: date = date.minusDays(6);
                datePicker_adapter = new DatePicker_Adapter(arrDate, getContext(), 6);
                break;
        }
        for(int i = 0; i < 7; i++){
            arrDate.add(new HabitDate(date.plusDays(i)));
        }
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rcv_DatePicker.setLayoutManager(linearLayoutManager);



        // xử lý onClickItem trong datePicker
        datePicker_adapter.setOnItemClickListener(new DatePicker_Adapter.OnItemClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onItemClick(LocalDate date) {
                habitsListAnytime.clear();
                habitItemAnytimeAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListAnytime = HabitDataBase.getInstance(getContext()).habitDAO().getAnytimeHabits(date.getDayOfWeek());
                habitItemAnytimeAdapter.notifyDataSetChanged();
                habitsListMorning.clear();
                habitItemMorningAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListMorning = HabitDataBase.getInstance(getContext()).habitDAO().getMorningHabits(date.getDayOfWeek());
                habitItemMorningAdapter.notifyDataSetChanged();
                habitsListAfternoon.clear();
                habitItemAfterNoonAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListAfternoon = HabitDataBase.getInstance(getContext()).habitDAO().getAfternoonHabits(date.getDayOfWeek());
                habitItemAfterNoonAdapter.notifyDataSetChanged();
                habitsListEvening.clear();
                habitItemEveningAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListEvening = HabitDataBase.getInstance(getContext()).habitDAO().getEveningHabits(date.getDayOfWeek());
                habitItemEveningAdapter.notifyDataSetChanged();
                habitsListCompleted.clear();
                habitItemCompletedAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListCompleted = HabitDataBase.getInstance(getContext()).habitDAO().getDoneHabits(date.getDayOfWeek());
                habitItemCompletedAdapter.notifyDataSetChanged();
                habitsListFailed.clear();
                habitItemFailedAdapter.updateDayOfWeek(date.getDayOfWeek());
                habitsListFailed = HabitDataBase.getInstance(getContext()).habitDAO().getFailedHabits(date.getDayOfWeek());
                habitItemFailedAdapter.notifyDataSetChanged();
            }
        });

        rcv_DatePicker.setAdapter(datePicker_adapter);
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
                habitItemAdapter.removeItem(position);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            } else if (direction == ItemTouchHelper.RIGHT) {
                habitItemAdapter.removeItem(position);
                Toast.makeText(getContext(), "Check Done", Toast.LENGTH_SHORT).show();
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
}