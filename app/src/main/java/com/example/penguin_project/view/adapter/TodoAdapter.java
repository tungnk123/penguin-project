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

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

        private List<String> itemList;

        public TodoAdapter(List<String> itemList) {
            this.itemList = itemList;
        }

        @NonNull
        @Override
        public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
            return new TodoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
            String item = itemList.get(position);
            holder.textView.setText(item);
        }

        @Override
        public int getItemCount() {
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
                        itemList.remove(position);
                        notifyItemRemoved(position);
                    }
                });
            }
        }
    }
