package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class third extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(third.this, four.class);
            startActivity(intent);

        });

    }
}