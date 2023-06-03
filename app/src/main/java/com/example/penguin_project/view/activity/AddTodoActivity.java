package com.example.penguin_project.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.penguin_project.MainActivity;
import com.example.penguin_project.R;
import com.example.penguin_project.model.repo.local.Table.Todo;
import com.example.penguin_project.view.adapter.CompletedTodoAdapter;
import com.example.penguin_project.view.fragment.TodoFragment;
import com.example.penguin_project.viewmodel.TodoViewModel;

import java.util.Random;

public class AddTodoActivity extends AppCompatActivity {

    public Button btnSave;
    public TodoViewModel todoViewModel;

    public EditText etTodoName;
    public EditText etDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);


        //region Anh xa xml
        etTodoName = (EditText) findViewById(R.id.et_activityAddTodo_todoName);
        etDesc = (EditText) findViewById(R.id.et_activityAddTodo_description);
        //endregion

        //region View Model
        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);

        //endregion

        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_activityAddTodo_toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to TodoFragment
                onBackPressed();
            }
        });

        btnSave = (Button) findViewById(R.id.btn_activityAddTodo_saveButton);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoName = etTodoName.getText().toString();
                String description = etDesc.getText().toString();

                Todo todo = new Todo(todoName, description, false);
                todoViewModel.insertTodo(todo);
                onBackPressed();
            }
        });
    }
}