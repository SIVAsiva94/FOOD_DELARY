package com.example.fooddelary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Reviews extends AppCompatActivity {
    DatabaseReference databaseReference;
    String Review;
    String Date;
    List<Reviewmodel> list=new ArrayList<Reviewmodel>();
    private RecyclerView recyclerView;
    Review_Adapter adapter;
    public static final String Database_path="Review";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);
        ImageButton btn1=findViewById(R.id.review_back);
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_path);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Reviews.this,homepage.class);
                startActivity(i);
            }
        });
        EditText input_review=findViewById(R.id.input_review);
        ImageButton submit=findViewById(R.id.submit_review);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reviewmodel reviewmodel=new Reviewmodel();
                GetDataFromEditText();
                reviewmodel.setReview(Review);
                reviewmodel.setReviewtime(Date);
                String review=databaseReference.push().getKey();
                databaseReference.child(review).setValue(reviewmodel);
            }
            private void GetDataFromEditText() {
                Review=input_review.getText().toString().trim();
            }
        });
      //  recyclerView=findViewById(R.id.review_rv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Reviewmodel reviewmodel=dataSnapshot.getValue(Reviewmodel.class);
                    list.add(reviewmodel);
                }
                adapter=new Review_Adapter(Reviews.this,list);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}