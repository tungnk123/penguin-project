package com.example.penguin_project.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.penguin_project.R;

public class SuggestFeatureActivity extends AppCompatActivity {

    public Button btnContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_feature);

        btnContact = findViewById(R.id.btn_acvivityReport_btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + "doanthanhtungnk123@gmail.com"));
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Suggest feature");
                    intent.putExtra(Intent.EXTRA_TEXT, "your_text");
                    startActivity(intent);
                } catch (ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });
    }
}