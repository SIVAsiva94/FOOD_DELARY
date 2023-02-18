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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    EditText inputEmail,inputPass;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    LoginDb db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inputEmail=findViewById(R.id.email1);
        inputPass= findViewById(R.id.pass1);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db=new LoginDb(this);

        Button button = findViewById(R.id.Forgotpass);
        button.setOnClickListener(view -> {

            Intent intent = new Intent(login.this, forgetpass.class);
            startActivity(intent);

        });
        Button signin=findViewById(R.id.signin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuthentication();
            }
        });
    }

    private void PerformAuthentication() {
        String email=inputEmail.getText().toString();
        String pass=inputPass.getText().toString();
        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter Correct Email");
        }
        else if(pass.isEmpty()|| pass.length()<8)
        {
            inputPass.setError("Input porper password");
        }else{

            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        String a="1";
                        String noTXT=a;
                        String mailTXT=inputEmail.getText().toString();
                        String passTXT=inputPass.getText().toString();
                        Boolean checkinsertdata=db.insertdata(noTXT,mailTXT,passTXT);
                        if(checkinsertdata==true)
                        {
                            Toast.makeText(login.this,"login failed",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(login.this,"login secuessfully",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                        Intent intent = new Intent(login.this, homepage.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(login.this,"Login  Successfully",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(login.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}