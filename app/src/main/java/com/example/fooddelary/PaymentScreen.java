package com.example.fooddelary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaymentScreen extends AppCompatActivity {
EditText holder_name,card_number,card_expiry;
DatabaseReference databaseReference;
String  Name,Number,Date,Time,Notification;
public static final String Database_path="Payment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_screen);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.cart);
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
        holder_name=findViewById(R.id.card_holder_name);
        card_number=findViewById(R.id.card_number);
        card_expiry=findViewById(R.id.expiry_data);
        Button pay=findViewById(R.id.pay);

        databaseReference = FirebaseDatabase.getInstance().getReference(Database_path);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               PaymentArray paymentArray = new PaymentArray();

                GetDataFromEditText();


                paymentArray.setCard_holder_name(Name);

                paymentArray.setOrder_date(Time);
                paymentArray.setNoti(Notification);
                paymentArray.setCard_number(Number);
                paymentArray.setCard_expiry(Date);




                String paymentRecord= databaseReference.push().getKey();


                databaseReference.child(paymentRecord).setValue(paymentArray);

                // Showing Toast message after successfully data submit.
                Toast.makeText(PaymentScreen.this,"Data Inserted Successfully into Firebase Database", Toast.LENGTH_LONG).show();

                Intent i=new Intent(PaymentScreen.this,MainActivity2.class);
                startActivity(i);
            }
        });
    }

    private void GetDataFromEditText() {
        Name=holder_name.getText().toString().trim();
        Number=card_number.getText().toString().trim();
        Date=card_expiry.getText().toString().trim();


    }
}