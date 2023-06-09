package com.example.penguin_project.view.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;

import java.time.LocalDate;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {
    private List<LocalDate> days;

    public CalendarAdapter(List<LocalDate> days) {
        this.days = days;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Đặt ngày và tô màu nếu đã hoàn thành công việc
        //holder.dayTextView.setText(String.valueOf(day.getDayOfMonth()));
//        if (day.isCompleted()) {
//            holder.dayTextView.setBackgroundColor(Color.GREEN);
//        } else {
//            holder.dayTextView.setBackgroundColor(Color.WHITE);
//        }
        LocalDate day = days.get(position);

        if (day != null) {
            holder.dayTextView.setText(String.valueOf(day.getDayOfMonth()));
        } else {
            holder.dayTextView.setText("");
            holder.dayTextView.setBackgroundColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dayTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            dayTextView = itemView.findViewById(R.id.dayTextView);
        }
    }
}