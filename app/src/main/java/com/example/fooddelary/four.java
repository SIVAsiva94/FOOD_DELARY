package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class four extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        Button button = findViewById(R.id.button4);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(four.this, create.class);
            startActivity(intent);

        });

    }
}