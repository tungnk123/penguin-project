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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.view.adapter.CompletedTodoAdapter;
import com.example.penguin_project.view.adapter.TodoAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class TodoFragment extends Fragment{

    private RecyclerView recyclerView;
    private ListView lvCompletedTodo;
    private TodoAdapter todoAdapter;
    private CompletedTodoAdapter completedTodoAdapter;
    private static List<Todo> todoList;
    private static List<Todo> completedTodoList;
    public int draggedItemIndex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = view.findViewById(R.id.rcv_todoFragment_todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        todoList = new ArrayList<>();
        todoList.add(new Todo(1, "Uong nuoc", "abcccccc", true));
        todoList.add(new Todo(2, "Code", "abcccccc", true));
        todoList.add(new Todo(3, "Doc sach", "abcccccc", true));


        todoAdapter = new TodoAdapter(todoList, this);
        recyclerView.setAdapter(todoAdapter);

        // Set up swipe functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // completed todo
        lvCompletedTodo = view.findViewById(R.id.lv_todoFragment_todoCompletedList);
        completedTodoList = new ArrayList<>();
        completedTodoList.add(new Todo(1, "Ăn uống", "abcccccc", true));
        completedTodoList.add(new Todo(2, "coi phim", "abcccccc", true));;
        completedTodoList.add(new Todo(3, "Chơi game", "abcccccc", true));

        completedTodoAdapter = new CompletedTodoAdapter(this.getContext(), R.layout.completed_todo_item, completedTodoList, this);
        lvCompletedTodo.setAdapter(completedTodoAdapter);

        //
        return view;
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
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(
              ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.END | ItemTouchHelper.START
            );
        }
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

            draggedItemIndex = viewHolder.getAdapterPosition();
            int targetIndex = target.getAdapterPosition();

            Collections.swap(todoList, draggedItemIndex, targetIndex);
            todoAdapter.notifyItemMoved(draggedItemIndex, targetIndex);

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

    public void moveItemToCompletedList(int position) {
        Todo item = todoList.get(position);
        todoList.remove(position);
        todoAdapter.notifyItemRemoved(position);

        // Add the item to the completedTodoList
        completedTodoList.add(item);
        completedTodoAdapter.notifyDataSetChanged();
    }

    public void moveCompletedItemToTodoList(int position) {

        Todo item = completedTodoList.get(position);
        completedTodoList.remove(position);
        completedTodoAdapter.notifyDataSetChanged();

        todoList.add(item);
        todoAdapter.notifyItemInserted(position);

    }
}
