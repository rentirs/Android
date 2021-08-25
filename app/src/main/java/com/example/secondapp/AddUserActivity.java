package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddUserActivity extends AppCompatActivity {

    Button buttonSave;
    EditText name;
    EditText lastName;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String saveOrEdit = getIntent().getStringExtra("msg");
        User userEdit = (User) getIntent().getSerializableExtra("user");

        super.onCreate(savedInstanceState);
        setTitle(saveOrEdit.equals("add") ? "Добавление" : "Редактирование" + " пользователя");
        setContentView(R.layout.activity_add_user);
        buttonSave = findViewById(R.id.buttonSave);
        name = findViewById(R.id.name);
        lastName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phone);
        if (saveOrEdit.equals("edit")) {
            name.setText(userEdit.getName());
            lastName.setText(userEdit.getLastName());
            phone.setText(userEdit.getPhone());
        }

        buttonSave.setOnClickListener(view -> {
            User user = saveOrEdit.equals("add") ? new User() : userEdit;
            user.setName(name.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setPhone(phone.getText().toString());
            Users users = new Users(this);
            if (saveOrEdit.equals("add")) {
                users.addUser(user);
            } else {
                users.updateUser(user);
            }
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

}