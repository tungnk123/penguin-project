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
import com.example.penguin_project.model.repo.local.Table.HabitOfTheme;

import java.util.ArrayList;

public class HabitOfTheme_Adapter extends ArrayAdapter<HabitOfTheme> {
    private Context mContext;
    private int mResource;

    public HabitOfTheme_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<HabitOfTheme> objects) {
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
        HabitThemeName.setText(getItem(position).getTitle());
        ImageHabitTheme.setImageResource(getItem(position).getThemeImage());
        return convertView;
    }
}
