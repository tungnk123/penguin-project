package com.example.penguin_project.view.fragment;

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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView rcv_DatePicker, rcv_HabitListAnytime;
    TextView txt_DayOfMonth, txt_DayOfWeek;
    TextView txt_Coin;
    ArrayList<HabitDate> arrDate;
    DatePicker_Adapter datePicker_adapter;
    List<Habits> habitsList;
    HabitItemAdapter habitItemAnytimeAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        rcv_DatePicker = view.findViewById(R.id.rcv_datePicker);
        rcv_HabitListAnytime = view.findViewById(R.id.rcv_Home_AnytimeHabit);
        txt_DayOfWeek = view.findViewById(R.id.txt_ActionBar_DayOfWeek);
        txt_DayOfMonth = view.findViewById(R.id.txt_ActionBar_DayOfMonth);
        txt_Coin = view.findViewById(R.id.txt_ActionBar_coin);

        Setting_DayOfWeekAndDayOfMonth();
        Setting_rcvDatePicker();
        Setting_rcvHabitList();

        return view;
    }

    private void Setting_rcvHabitList() {
        habitsList = new ArrayList<>();
        habitsList.add(new Habits(4, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsList.add(new Habits(1, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));
        habitsList.add(new Habits(2, "Uong nuoc", 1, 4, 1, R.color.purple_200, R.mipmap.icon_water, LocalDate.now(), 0, 3));

        habitItemAnytimeAdapter = new HabitItemAdapter(habitsList);

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

        datePicker_adapter.setOnItemClickListener(new DatePicker_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(LocalDate date) {
                Toast.makeText(getContext(), date.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        rcv_DatePicker.setAdapter(datePicker_adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(rcv_DatePicker);
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
            if (direction == ItemTouchHelper.LEFT) {
                habitItemAnytimeAdapter.removeItem(position);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_SHORT).show();
            } else if (direction == ItemTouchHelper.RIGHT) {
                habitItemAnytimeAdapter.checkDoneItem(position);
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