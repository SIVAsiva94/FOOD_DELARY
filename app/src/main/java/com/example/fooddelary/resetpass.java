package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class resetpass extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpass);
        Button button = findViewById(R.id.resend);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(resetpass.this, vreifiedsucces.class);
            startActivity(intent);

        });
    }
}