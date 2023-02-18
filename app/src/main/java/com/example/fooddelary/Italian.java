package com.example.fooddelary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Italian extends AppCompatActivity {
    DatabaseReference mbase;
    ProgressDialog progressDialog;
    List<person> list = new ArrayList<>();
    private RecyclerView recyclerView1;
    personAdapter adapter1;
    public static final String Database_Path = "Foodlist";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_italian);
        ImageButton btn=findViewById(R.id.italian_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Italian.this,homepage.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.wishlist:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.cart:
                        startActivity(new Intent(getApplicationContext(),Cart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(),Account.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
        mbase = FirebaseDatabase.getInstance().getReference(Database_Path);
        recyclerView1 = findViewById(R.id.card_italian);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView1.setLayoutManager(gridLayoutManager);
        progressDialog = new ProgressDialog(Italian.this);
        progressDialog.setMessage("Loading Data from Firebase Database");
        progressDialog.show();
        mbase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    person studentDetails = dataSnapshot.getValue(person.class);

                    list.add(studentDetails);
                }

                adapter1 = new personAdapter(Italian.this, list);
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