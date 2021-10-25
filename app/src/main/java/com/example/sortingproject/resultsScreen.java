package com.example.sortingproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class resultsScreen extends AppCompatActivity {

    TextView messageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_screen);

        messageTextView = findViewById(R.id.messageTextView);

        String message = "";
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                message = "error: display message not found";
            } else {
                message = extras.getString("MESSAGE");
            }
        }
        messageTextView.setText(message);
    }
}