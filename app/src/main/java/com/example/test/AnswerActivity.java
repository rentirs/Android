package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        setTitle("Ответ");

        boolean isAnswerTrue = getIntent().getBooleanExtra("answer", false);

        TextView answerTextView = findViewById(R.id.answerTextView);
        answerTextView.setText(isAnswerTrue ? R.string.yes : R.string.no);
    }
}