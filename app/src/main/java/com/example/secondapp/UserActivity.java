package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        setTitle("Выбраный пользователь");

        TextView selectItem = findViewById(R.id.selectItem);
        User user = (User) getIntent().getSerializableExtra("selectItem");
        selectItem.setText(user.getName() + " " + user.getLastName() + "\n" + "Телефон: " + user.getPhone());

        Button delete = findViewById(R.id.delete);
        Users users = new Users(this);
        delete.setOnClickListener(view -> {
            users.deleteUser(user.getUuid());
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button edit = findViewById(R.id.edit);
        edit.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddUserActivity.class);
            intent.putExtra("msg", "edit");
            intent.putExtra("user", user);
            startActivity(intent);
        });
    }
}