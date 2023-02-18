package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView t=findViewById(R.id.textView4);
        ImageView i=findViewById(R.id.f1);
        ImageView i2=findViewById(R.id.imageView2);
        ImageView i3=findViewById(R.id.imageView3);
        ImageView i1=findViewById(R.id.imageView);
        Button button = findViewById(R.id.button3);
        String t1="Food delivery";
        String t2="Fresh Food";
        String t3="Easy payment";
        String a=t.getText().toString();
        button.setOnClickListener(view -> {


               t.setText("Fresh Food");
               i.setImageResource(R.drawable.eating_together_pana);

               i1.setImageResource(R.drawable.point);

               i2.setImageResource(R.drawable.ic_baseline_circle_24);
               button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {
                       t.setText("Easy payment");
                       i2.setImageResource(R.drawable.point);
                       i3.setImageResource(R.drawable.ic_baseline_circle_24);
                       i.setImageResource(R.drawable.plain_credit_card_amico);
                       button.setText("Get Started");
                       button.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               Intent intent =new Intent(SecondActivity.this,create.class);
                               startActivity(intent);
                           }
                       });
                   }
               });

        });




    }
}