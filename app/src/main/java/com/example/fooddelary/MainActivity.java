package com.example.fooddelary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 2000;
   private  LoginDb db;
   private ArrayList<Loginmodel> loginmodels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        db=new LoginDb(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String no="1";
                Boolean checkuser = db.checkusernames(no);
                if(checkuser==false){
                    Intent i=new Intent(MainActivity.this,SecondActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(MainActivity.this, homepage.class);
                    startActivity(i);
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}