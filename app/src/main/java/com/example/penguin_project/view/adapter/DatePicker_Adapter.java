package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitDate;

import java.time.LocalDate;
import java.util.ArrayList;

public class DatePicker_Adapter extends RecyclerView.Adapter<DatePicker_Adapter.DateViewHoder> {

    ArrayList<HabitDate> arrDate;
    Context context;
    private int selectedItemPosition = RecyclerView.NO_POSITION;

    public DatePicker_Adapter(ArrayList<HabitDate> arrDate, Context context, int selectedItemPosition){

        this.arrDate = arrDate;
        this.context = context;
        this.selectedItemPosition = selectedItemPosition;
    }

    @NonNull
    @Override
    public DateViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rcv_item, parent, false);
        return new DateViewHoder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull DateViewHoder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        HabitDate habitDate = arrDate.get(adapterPosition);
        LocalDate date = habitDate.getDate();

        switch (date.getDayOfWeek()){
            case MONDAY: holder.tv_DayOfWeek.setText("Mon");
                break;
            case TUESDAY: holder.tv_DayOfWeek.setText("Tue");
                break;
            case WEDNESDAY: holder.tv_DayOfWeek.setText("Wed");
                break;
            case THURSDAY: holder.tv_DayOfWeek.setText("Thu");
                break;
            case FRIDAY: holder.tv_DayOfWeek.setText("Fri");
                break;
            case SATURDAY: holder.tv_DayOfWeek.setText("Sat");
                break;
            case SUNDAY: holder.tv_DayOfWeek.setText("Sun");
                break;
        }
        holder.tv_DayOfMonth.setText(String.valueOf(date.getDayOfMonth()));

        if (adapterPosition == selectedItemPosition) {
            // Áp dụng giao diện khác biệt cho item đang được chọn
            holder.itemView.setBackgroundResource(R.drawable.rcv_date_item_shape_selected);
        } else {
            // Áp dụng giao diện cho các item còn lại
            holder.itemView.setBackgroundResource(R.drawable.rcv_date_item_shape_default);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener != null){
                    listener.onItemClick(date);
                }
                if(adapterPosition != RecyclerView.NO_POSITION){
                    selectedItemPosition = adapterPosition;
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrDate.size();
    }

    public static class DateViewHoder extends RecyclerView.ViewHolder{
        private TextView tv_DayOfWeek, tv_DayOfMonth;
        private LinearLayout lnl_parent;
        public DateViewHoder(@NonNull View itemView) {
            super(itemView);
            tv_DayOfWeek = itemView.findViewById(R.id.tvDayOfWeek);
            tv_DayOfMonth = itemView.findViewById(R.id.tvDayOfMonth);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(LocalDate date);
    }

    private OnItemClickListener listener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
