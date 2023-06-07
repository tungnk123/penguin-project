package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.penguin_project.R;
import com.example.penguin_project.model.data.HabitTheme;

import java.util.ArrayList;

public class HabitTheme_Adapter extends ArrayAdapter<HabitTheme> {

    private Context mContext;
    private int mResource;

    public HabitTheme_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<HabitTheme> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);
        TextView HabitThemeName = convertView.findViewById(R.id.tvHabitThemeName);
        ImageView ImageHabitTheme = convertView.findViewById(R.id.ivHabitThemeImage);
        HabitThemeName.setText(getItem(position). getHabitThemeName());
        ImageHabitTheme.setImageResource(getItem(position).getImageHabitTheme());
        return convertView;
    }
}
