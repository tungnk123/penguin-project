package com.example.penguin_project.view.adapter;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.view.fragment.TodoFragment;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {


    public OnItemClickListener mListener;
    private final List<Todo> itemList;
    private final TodoFragment todoFragment;


    public TodoAdapter(List<Todo> itemList, TodoFragment todoFragment, OnItemClickListener listener) {
        this.itemList = itemList;
        this.todoFragment = todoFragment;
        mListener = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo item = itemList.get(position);
        holder.textView.setText(item.getTitle());
        holder.cbIsDone.setChecked(item.getIsDone());

    }

    @Override
    public int getItemCount() {
        if (itemList == null) {
            return 0;
        }
        return itemList.size();
    }

    public void removeItem(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox cbIsDone;

        TodoViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_todoItem_todoTitle);
            cbIsDone = itemView.findViewById(R.id.cb_todoItem_checkDone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && mListener != null) {
                        mListener.onItemClick(position);
                    }
                }
            });

            cbIsDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        todoFragment.moveItemToCompletedList(position);
                    }
                }
            });
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
