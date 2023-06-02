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

        private List<Todo> itemList;
        private TodoFragment todoFragment;

        public TodoAdapter(List<Todo> itemList, TodoFragment todoFragment) {
            this.itemList = itemList;
            this.todoFragment = todoFragment;
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

                cbIsDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    // khi nhan button done
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        todoFragment.moveItemToCompletedList(position);
                    }
                });
            }
        }
    }
