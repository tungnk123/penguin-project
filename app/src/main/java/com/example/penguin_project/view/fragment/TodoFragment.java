package com.example.penguin_project.view.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.R;

import java.util.ArrayList;
import java.util.List;


public class TodoFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private List<String> todoList;
//    private  List<String> completedTodoList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = view.findViewById(R.id.rcv_todoFragment_todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        todoList = new ArrayList<>();
        todoList.add("Task 1");
        todoList.add("Task 2");
        todoList.add("Task 3");

        todoAdapter = new TodoAdapter(todoList);
        recyclerView.setAdapter(todoAdapter);

        // Set up swipe functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    private class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

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
            Button buttonAction;

            TodoViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.tv_todoItem_todoTitle);
                buttonAction = itemView.findViewById(R.id.btn_todoFragment_doneButton);

                buttonAction.setOnClickListener(new View.OnClickListener() {
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

    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

        private Drawable checkDoneIcon;
        private Drawable deleteIcon;
        private final ColorDrawable checkDoneBackground;
        private final ColorDrawable deleteBackground;

        public SwipeToDeleteCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            checkDoneIcon = ContextCompat.getDrawable(requireContext(), R.drawable.icon_coin);
            deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.icon_coin);
            checkDoneBackground = new ColorDrawable(Color.GREEN);
            deleteBackground = new ColorDrawable(Color.RED);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            Toast.makeText(getContext(), "move", Toast.LENGTH_LONG).show();

            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            todoAdapter.removeItem(position);
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            View itemView = viewHolder.itemView;
            int backgroundCornerOffset = 20;

            if (dX > 0) { // Swiping to the right (Check Done)
                int iconMargin = (itemView.getHeight() - checkDoneIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - checkDoneIcon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + checkDoneIcon.getIntrinsicHeight();

                int iconLeft = itemView.getLeft() + iconMargin;
                int iconRight = itemView.getLeft() + iconMargin + checkDoneIcon.getIntrinsicWidth();
                checkDoneIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                checkDoneBackground.setBounds(itemView.getLeft(), itemView.getTop(),
                        itemView.getLeft() + ((int) dX) + backgroundCornerOffset, itemView.getBottom());
            } else if (dX < 0) { // Swiping to the left (Delete)
                int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

                int iconLeft = itemView.getRight() - iconMargin - deleteIcon.getIntrinsicWidth();
                int iconRight = itemView.getRight() - iconMargin;
                deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                deleteBackground.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                        itemView.getTop(), itemView.getRight(), itemView.getBottom());
            } else { // View is unswiped
                checkDoneBackground.setBounds(0, 0, 0, 0);
                deleteBackground.setBounds(0, 0, 0, 0);
            }

            checkDoneBackground.draw(c);
            deleteBackground.draw(c);
            checkDoneIcon.draw(c);
            deleteIcon.draw(c);
        }
    }
}
