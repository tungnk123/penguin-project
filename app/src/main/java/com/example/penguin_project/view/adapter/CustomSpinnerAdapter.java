package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.penguin_project.R;

public class CustomSpinnerAdapter extends ArrayAdapter<CharSequence> {
    private Context context;
    private int textViewResourceId;

    public CustomSpinnerAdapter(Context context, int resource, CharSequence[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.textViewResourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setTextSize(18); // Set the desired text size
        textView.setTextColor(context.getResources().getColor(R.color.white)); // Set the desired text color
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        textView.setTextSize(18);
        textView.setTextColor(context.getResources().getColor(R.color.white));
        return textView;
    }
}

