package com.example.fooddelary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Detail extends AppCompatActivity {

    ImageView fimage;
    TextView fdiscription;
    TextView cost;
    TextView rating;
    TextView name;
    String s6;
    int count=0;
    TextView t1;
    Button b1;
    ImageButton p1,p2;
    DBHelper1 DB1;
String s1,s2,s3,s4,s5,s7;
Integer i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name=findViewById(R.id.name);
        cost=findViewById(R.id.cost);
        t1=findViewById(R.id.d_quantity);
        rating=findViewById(R.id.rating);
        fdiscription=findViewById(R.id.descrip);
        Bundle mbundle=getIntent().getExtras();
        if(mbundle != null){
            name.setText(mbundle.getString("Name"));
            cost.setText(mbundle.getString("Cost"));
            rating.setText(mbundle.getString("Rating"));
            s6=mbundle.getString("Img");
            fdiscription.setText(mbundle.getString("Descrip"));
        }
        fimage=findViewById(R.id.detail_img);
        Glide.with(getApplicationContext()).load(s6).into(fimage);
        DB1=new DBHelper1(this);
        b1=findViewById(R.id.add_cart);
        p1=findViewById(R.id.d_pluse);
        p2=findViewById(R.id.d_minus);
        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                t1.setText(String.valueOf(count));
                DBHelper1 helper = new DBHelper1(Detail.this);
                s4=name.getText().toString();
                s5=String.valueOf(count);
                helper.update(s4,s5);
            }
        });
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count==0){
                        increment();
                    s1=name.getText().toString();
                    s2=cost.getText().toString();
                    s3=String.valueOf(count);
                    String nameTXT=s1;
                    String costTXT=s2;
                    String quantityTXT =s3;
                    String imgTXT=s6;
                    Boolean checkinsertdata=DB1.insertcartdata(nameTXT,costTXT,quantityTXT,imgTXT);
                    if(checkinsertdata==true)
                    {
                        Toast.makeText(Detail.this,"Item Added to cart",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(Detail.this,"Item updated secuessfully",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(count!=0){
                    count++;
                    t1.setText(String.valueOf(count));
                    DBHelper1 helper = new DBHelper1(Detail.this);
                    s4=name.getText().toString();
                    s5=String.valueOf(count);
                    helper.update(s4,s5);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==0) {
                    increment();
                }
                s1=name.getText().toString();
                s2=cost.getText().toString();
                s3=String.valueOf(count);

                String nameTXT=s1;
                String costTXT=s2;
                String quantityTXT =s3;
                String imgTXT=s6;
                Boolean checkinsertdata=DB1.insertcartdata(nameTXT,costTXT,quantityTXT,imgTXT);
                if(checkinsertdata==true)
                {
                    Toast.makeText(Detail.this,"Item Added to cart",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Detail.this,"Item already in cart",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void increment() {
        count++;
        t1.setText(""+count);
    }


}