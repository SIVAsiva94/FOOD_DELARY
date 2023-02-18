package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class verification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Button button = findViewById(R.id.verify);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(verification.this, resetpass.class);
            startActivity(intent);

        });
    }
}