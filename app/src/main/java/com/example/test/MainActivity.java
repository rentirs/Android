package com.example.test;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionTextView;
    final Question[] questions =
            {
                    new Question(R.string.question1, true),
                    new Question(R.string.question2, true),
                    new Question(R.string.question3, false),
                    new Question(R.string.question4, true),
                    new Question(R.string.question5, true)
            };
    int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
        questionIndex = savedInstanceState.getInt("questionIndex", 0);

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);
        questionTextView.setText(questions[questionIndex].getQuestionText());

        yesBtn.setOnClickListener(view -> {
            if (questions[questionIndex].isAnswerTrue())
                Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
            questionIndex = (questionIndex + 1) % questions.length;
            questionTextView.setText(questions[questionIndex].getQuestionText());
        });

        noBtn.setOnClickListener(view -> {
            if (questions[questionIndex].isAnswerTrue())
                Toast.makeText(MainActivity.this, "Неправильно!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
            questionIndex = (questionIndex + 1) % questions.length;
            questionTextView.setText(questions[questionIndex].getQuestionText());
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("questionIndex", questionIndex);
    }
}