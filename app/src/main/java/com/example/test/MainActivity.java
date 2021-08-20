package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionTextView;
    Button showAnswer;
    int correctAnswers;

    final Question[] questions =
            {
                    new Question(R.string.question1, true),
                    new Question(R.string.question2, true),
                    new Question(R.string.question3, false),
                    new Question(R.string.question4, true),
                    new Question(R.string.question5, true)
            };

    final ArrayList<String> results = new ArrayList();
    int questionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("questionIndex", 0);

        correctAnswers = 0;
        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);
        showAnswer = findViewById(R.id.showAnswer);
        questionTextView.setText(questions[questionIndex].getQuestionText());

        yesBtn.setOnClickListener(view -> checkAnswer(true));
        noBtn.setOnClickListener(view -> checkAnswer(false));

        showAnswer.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AnswerActivity.class);
            intent.putExtra("answer", questions[questionIndex].isAnswerTrue());
            startActivity(intent);
        });
    }

    private void checkAnswer(boolean btn) {
        if ((questions[questionIndex].isAnswerTrue() && btn) || (!questions[questionIndex].isAnswerTrue() && !btn)) {
            Toast.makeText(MainActivity.this, "Правильно!", Toast.LENGTH_SHORT).show();
            correctAnswers++;
        } else {
            Toast.makeText(MainActivity.this, "Не правильно!", Toast.LENGTH_SHORT).show();
        }

        results.add("Вопрос: " + questionTextView.getText() + "\nВаш ответ: " + (btn ? "Да" : "Нет") + "\n");
        questionIndex = (questionIndex + 1) % questions.length;

        if (questionIndex == 0) {
            Intent intentResults = new Intent(MainActivity.this, ResultsActivity.class);
            intentResults.putExtra("trueAnswers", correctAnswers);
            intentResults.putExtra("results", results);
            startActivity(intentResults);
            correctAnswers = questionIndex == 0 ? 0 : correctAnswers;
            results.clear();
        }
        questionTextView.setText(questions[questionIndex].getQuestionText());
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO: ", "onSaveInstanceState: ");
        savedInstanceState.putInt("questionIndex", questionIndex);
    }


}