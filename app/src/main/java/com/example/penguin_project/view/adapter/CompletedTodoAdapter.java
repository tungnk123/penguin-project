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
import com.example.penguin_project.view.fragment.TodoFragment;

import java.util.List;


public class CompletedTodoAdapter extends ArrayAdapter<Todo> {
    public List<Todo> completedTodoList;
    public TodoFragment todoFragment;
    int resource;


    public CompletedTodoAdapter(Context context, int resource, List<Todo> completedTodoList, TodoFragment todoFragment) {
        super(context, resource, completedTodoList);
        this.resource = resource;
        this.completedTodoList = completedTodoList;
        this.todoFragment = todoFragment;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TodoViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);

            holder = new TodoViewHolder();
            holder.tvTitle = convertView.findViewById(R.id.tv_completedTodoItem_todoTitle);
            holder.cbIsDone = convertView.findViewById(R.id.cb_completedTodoItem_checkDone);

            convertView.setTag(holder);
        } else {
            holder = (TodoViewHolder) convertView.getTag();
        }

        Todo todo = getItem(position);
        if (todo != null) {
            holder.tvTitle.setText(todo.getTitle().toString());
            holder.tvTitle.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            holder.cbIsDone.setChecked(todo.getIsDone());
            holder.cbIsDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    todoFragment.moveCompletedItemToTodoList(position);
                }
            });
        }

        return convertView;
    }
    public static class TodoViewHolder {
        TextView tvTitle;
        CheckBox cbIsDone;
    }
}
