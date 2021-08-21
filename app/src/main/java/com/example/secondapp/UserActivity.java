package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Выбраный пользователь");

        TextView selectItem = findViewById(R.id.selectItem);
        selectItem.setText(getIntent().getStringExtra("selectItem"));
    }
}