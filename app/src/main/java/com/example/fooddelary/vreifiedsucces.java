package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class vreifiedsucces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vreifiedsucces);
        Button button = findViewById(R.id.done);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(vreifiedsucces.this, login.class);
            startActivity(intent);

        });
    }
}