package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.TimeOFWeek;

import java.util.ArrayList;
import java.util.List;

public class CreateHabit_DateOfWeekPicker_Adapter extends RecyclerView.Adapter<CreateHabit_DateOfWeekPicker_Adapter.ViewHolder> {

    private Context context;

    private ArrayList<TimeOFWeek> TimeOfWeeks;


    public CreateHabit_DateOfWeekPicker_Adapter(Context context, ArrayList<TimeOFWeek> TimeOfWeeks){
        this.context = context;
        this.TimeOfWeeks = TimeOfWeeks;
    }

    public void setTimeOfWeeks(ArrayList<TimeOFWeek> timeOFWeeks) {
        this.TimeOfWeeks.clear();
        this.TimeOfWeeks.addAll(timeOFWeeks);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dayButton;

        public ViewHolder(View itemView) {
            super(itemView);
            dayButton = itemView.findViewById(R.id.tvTimeOfWeek);
        }

        void bind(final TimeOFWeek timeOFWeek) {
            dayButton.setBackgroundResource(timeOFWeek.isChecked() ? R.drawable.rcv_timeofweek_item_shape_default : R.drawable.rcv_timeofweek_item_shape_selected);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    timeOFWeek.setChecked(!timeOFWeek.isChecked());
                    dayButton.setBackgroundResource(timeOFWeek.isChecked() ?  R.drawable.rcv_timeofweek_item_shape_default : R.drawable.rcv_timeofweek_item_shape_selected);
                }
            });
        }
    }

    public ArrayList<TimeOFWeek> getAll() {return TimeOfWeeks;}

    public ArrayList<TimeOFWeek> selected = new ArrayList<>();

    public ArrayList<TimeOFWeek> getSelected() {
        for (int i = 0; i < TimeOfWeeks.size(); i++){
            if (TimeOfWeeks.get(i).isChecked()){
                selected.add(TimeOfWeeks.get(i));
            }
        }
        return selected;
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        LayoutInflater inflater =LayoutInflater.from(context);

        View timeOfWeekView = inflater.inflate(R.layout.layout_daybutton_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(timeOfWeekView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(TimeOfWeeks.get(position));

    }

    @Override
    public int getItemCount() {
        return TimeOfWeeks.size();
    }


}
