package com.example.fooddelary;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class homepage extends AppCompatActivity {
    DatabaseReference mbase;
    ProgressDialog progressDialog;
    List<person> list = new ArrayList<>();
    private RecyclerView recyclerView1;
    personAdapter adapter1;
    public static final String Database_Path = "Foodlist";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button btn1 = findViewById(R.id.vegtarian);
        Button btn2 = findViewById(R.id.italian);
        Button btn3 = findViewById(R.id.chines);
        ImageButton btn4=findViewById(R.id.notifiaction);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(homepage.this,Notification1.class);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, veg.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, Italian.class);
                startActivity(i);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(homepage.this, Chines.class);
                startActivity(i);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(), Favorites.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), homepage.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(), Cart.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Account.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        Button button = findViewById(R.id.popular);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(homepage.this, Popular.class);
            startActivity(intent);

        });

        MyListData[] myListData = new MyListData[]{
                new MyListData(R.drawable.food),
                new MyListData(R.drawable.food2),
                new MyListData(R.drawable.food),
                new MyListData(R.drawable.food2),
                new MyListData(R.drawable.food),
                new MyListData(R.drawable.gril),
                new MyListData(R.drawable.food),
                new MyListData(R.drawable.pasta),
                new MyListData(R.drawable.gril),
                new MyListData(R.drawable.pasta),
        };
        RecyclerView recyclerView = findViewById(R.id.cardrecyclerview);
        MyListAdapter adapter = new MyListAdapter(myListData);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
        final int interval_time = 4000;
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                if (count < myListData.length) {
                    recyclerView.scrollToPosition(count++);
                    handler.postDelayed(this, interval_time);
                    if (count == myListData.length) {
                        count = 0;
                    }
                }
            }
        };
        handler.postDelayed(runnable, interval_time);
        mbase = FirebaseDatabase.getInstance().getReference(Database_Path);
        recyclerView1 = findViewById(R.id.cardrecyclerview1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(homepage.this);
        progressDialog.setMessage("Loading Data from Firebase Database");
        progressDialog.show();
      mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    person studentDetails = dataSnapshot.getValue(person.class);
                    list.add(studentDetails);
                }
                adapter1 = new personAdapter(homepage.this, list);
                recyclerView1.setAdapter(adapter1);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();
            }
        });
    }
}
