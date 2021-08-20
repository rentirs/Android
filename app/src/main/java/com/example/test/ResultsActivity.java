package com.example.test;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {
    int numOfCorrectAnswers;
    ArrayList<String> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        setTitle("Результаты");

        numOfCorrectAnswers = getIntent().getIntExtra("trueAnswers", 0);
        results = getIntent().getStringArrayListExtra("results");

        TextView correctAnswers = findViewById(R.id.correctAnswers);
        correctAnswers.setText(R.string.numberOfCorrectAnswer);
        correctAnswers.append(" " + numOfCorrectAnswers);
        TextView question = findViewById(R.id.question);
        for (String q : results) {
            question.append(q + "\n");
        }
    }
}