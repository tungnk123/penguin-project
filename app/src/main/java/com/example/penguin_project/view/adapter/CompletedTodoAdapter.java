package com.example.penguin_project.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;

import java.util.List;

public class CompletedTodoAdapter extends ArrayAdapter<Todo> {
    public List<Todo> completedTodoList;
    int resource;

    public CompletedTodoAdapter(Context context, int resource, List<Todo> completedTodoList) {
        super(context, resource, completedTodoList);
        this.resource = resource;
        this.completedTodoList = completedTodoList;
    }
    @NonNull
    @Override
    public View getView(int position, @NonNull View contextView, @NonNull ViewGroup viewGroup) {
        View view = contextView;
        LayoutInflater layoutInflater = LayoutInflater.from(this.getContext());
        view = layoutInflater.inflate(this.resource, null);

        Todo todo = getItem(position);
        if (todo != null) {
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_completedTodoItem_todoTitle);
            CheckBox cbIsDone = (CheckBox) view.findViewById(R.id.cb_completedTodoItem_checkDone);

            if (tvTitle != null) {
                tvTitle.setText(todo.getTitle().toString());
                tvTitle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }
            if (cbIsDone != null) {
                cbIsDone.setChecked(todo.getIsDone());
            }
        }
        return view;

    }
}
