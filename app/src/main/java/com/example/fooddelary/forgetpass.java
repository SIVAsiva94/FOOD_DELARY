package com.example.fooddelary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forgetpass extends AppCompatActivity {
    EditText inputEmail;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    Button sendcode;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpass);
        inputEmail=findViewById(R.id.forgetpass);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        back=findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(forgetpass.this, login.class);
                startActivity(intent);
            }
        });

      sendcode=findViewById(R.id.send1);
        sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuthentication();

            }
        });

    }

    private void PerformAuthentication() {
        String email=inputEmail.getText().toString();
        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email");
        }else{
            FirebaseAuth  auth=FirebaseAuth.getInstance();
            auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Intent intent = new Intent(forgetpass.this, login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(forgetpass.this,"Check your email",Toast.LENGTH_SHORT).show();

                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(forgetpass.this,""+task.getException(),Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }
    }
}