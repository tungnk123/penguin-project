package com.example.penguin_project.view.fragment;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

import com.example.penguin_project.MainActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.model.data.CoinManager;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.view.activity.AddTodoActivity;
import com.example.penguin_project.view.adapter.CompletedTodoAdapter;
import com.example.penguin_project.view.adapter.TodoAdapter;
import com.example.penguin_project.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class TodoFragment extends Fragment implements TodoAdapter.OnItemClickListener {

    TodoViewModel todoViewModel;
    private RecyclerView recyclerView;
    private ListView lvCompletedTodo;
    private TodoAdapter todoAdapter;
    private CompletedTodoAdapter completedTodoAdapter;
    private static List<Todo> todoList = new ArrayList<>();
    private static List<Todo> completedTodoList = new ArrayList<>();
    public int draggedItemIndex;
    public FloatingActionButton addTodoButton;
    private TextView txt_Coin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // TodoViewModel
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        // Set up observer de theo doi khi nao data thay doi thi observer dc trigger va update Ui voi thong tin moi
        todoViewModel.getTodoListByIsDone(false).observe(getViewLifecycleOwner(), todos -> {
            todoList.clear();
            todoList.addAll(todos);
            todoAdapter.notifyDataSetChanged();
        });

        todoViewModel.getTodoListByIsDone(true).observe(getViewLifecycleOwner(), completedTodos -> {
            completedTodoList.clear();
            completedTodoList.addAll(completedTodos);
            completedTodoAdapter.notifyDataSetChanged();
        });

        //
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        recyclerView = view.findViewById(R.id.rcv_todoFragment_todoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        todoAdapter = new TodoAdapter(todoList, this, new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO xu ly khi user nhan vao 1 todo se chuyen qua edit todo
                Toast.makeText(getContext(), "Item clicked at position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        todoAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(todoAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerView.SimpleOnItemTouchListener());

        // Set up swipe functionality
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // completed TodoItem
        lvCompletedTodo = view.findViewById(R.id.lv_todoFragment_todoCompletedList);

        completedTodoAdapter = new CompletedTodoAdapter(this.getContext(), R.layout.completed_todo_item, completedTodoList, this);
        lvCompletedTodo.setAdapter(completedTodoAdapter);

        //
        txt_Coin = view.findViewById(R.id.txt_Todo_Coin);
        txt_Coin.setText(String.valueOf(CoinManager.getInstance(getContext()).getData("Coin", 100)));

        addTodoButton = (FloatingActionButton) view.findViewById(R.id.fabtn_fragmentTodo_addTodoButton);

        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddTodoActivity.class);
                intent.putExtra("type", "Add new Todo");
                intent.putExtra("delete button", "Off");
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onItemClick(int position) {

        Toast.makeText(getContext(), "Item clicked at position: " + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), AddTodoActivity.class);
        Todo todo = todoList.get(position);
        intent.putExtra("title", todo.getTitle());
        intent.putExtra("description", todo.getDescription());


        if (todo.getDueDate() != null) {

            intent.putExtra("due time", todo.getDueDate().toString());
        }
        if (todo.getRemindTime() != null) {
            intent.putExtra("remind time", todo.getRemindTime().toString());
        }
        intent.putExtra("type", "Edit Todo");
        intent.putExtra("delete button", "On");
        intent.putExtra("position", todo.getTodo_id());
        startActivity(intent);
    }


    private class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {

        private Drawable checkDoneIcon;
        private Drawable deleteIcon;
        private final ColorDrawable checkDoneBackground;
        private final ColorDrawable deleteBackground;

        public SwipeToDeleteCallback() {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            checkDoneIcon = ContextCompat.getDrawable(requireContext(), R.drawable.icon_done);
            deleteIcon = ContextCompat.getDrawable(requireContext(), R.drawable.icon_delete_24);
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

            // TODO: xu ly xoa notification khi tick done or delete
            int position = viewHolder.getAdapterPosition();
            Todo todo = todoList.get(position);
            if (direction == ItemTouchHelper.START) {
                // delete
                todoViewModel.deleteTodo(todo.getTodo_id());

            } else if (direction == ItemTouchHelper.END) {
                // check done
                moveItemToCompletedList(position);
            }
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

        todoViewModel.updataIsDoneById(true, item.getTodo_id());
//        // Add the item to the completedTodoList
//        completedTodoList.add(completedItem);
        completedTodoAdapter.notifyDataSetChanged();

    }

    public void moveCompletedItemToTodoList(int position) {

        Todo completedItem = completedTodoList.get(position);
        completedTodoList.remove(position);
        completedTodoAdapter.notifyDataSetChanged();

        todoViewModel.updataIsDoneById(false, completedItem.getTodo_id());
//
        todoAdapter.notifyDataSetChanged();

    }
}
