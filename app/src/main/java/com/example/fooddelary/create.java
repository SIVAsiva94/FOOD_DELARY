package com.example.fooddelary;

import static com.google.android.gms.auth.api.signin.GoogleSignInOptions.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class create extends AppCompatActivity {
    EditText inputEmail,inputPass,inputRepass,inputName;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseAuth mAuth;
    ImageButton btngoogle;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    private GoogleSignInClient mGoogleSignInClient;
    public static final int RC_SIGN_IN=123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

      inputName=findViewById(R.id.name1);
        inputEmail=findViewById(R.id.email1);
        inputPass= findViewById(R.id.pass1);
        inputRepass = findViewById(R.id.repass1);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        //LOGIN LISTNER
        Button button = findViewById(R.id.signin2);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(create.this, login.class);
            startActivity(intent);
        });

        //SIGNUP LISTENER;
        Button signup=findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuthentication();
            }
            });

        //GOOGLE SIGNIN
        btngoogle=findViewById(R.id.google);

        requestGoogleSignIn();
        btngoogle.setOnClickListener(view ->{
            signIn();
        });
        //FACEBOOK
        
    }
    //GOOGLE
    private  void requestGoogleSignIn(){
        GoogleSignInOptions gso= new Builder(DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
          mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
    }
    private void signIn(){
        Intent signInIntent= mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }
@Override
public  void onActivityResult(int requestCode,int resultCode,Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode==RC_SIGN_IN){
        Task<GoogleSignInAccount>task=GoogleSignIn.getSignedInAccountFromIntent(data);
        try{
            GoogleSignInAccount account=task.getResult(ApiException.class);
            firebaseAuthWithGoogle(account.getIdToken());
        } catch (ApiException e){
        }
    }
}
private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential= GoogleAuthProvider.getCredential(idToken,null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                           Intent intent=new Intent(create.this,homepage.class);
                           startActivity(intent);
                        }
                        else{
                           Toast.makeText(create.this,"email worng",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
// FACEBOOK;

// SIGN UP;
    private void PerformAuthentication() {
        String name=inputName.getText().toString();
        String email=inputEmail.getText().toString();
        String pass=inputPass.getText().toString();
        String repass=inputRepass.getText().toString();
        if(name.isEmpty())
        {
            inputName.setError("Enter your name");
        }
        else if(!email.matches(emailPattern))
        {
            inputEmail.setError("ENter Correct Email");
        }
        else if(pass.isEmpty()|| pass.length()<8)
        {
           inputPass.setError("Input porper password");
        }
        else if(repass.isEmpty() || !pass.equals(repass) )
        {
            inputRepass.setError("Password not match");
        }else
            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        Intent intent = new Intent(create.this, login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        Toast.makeText(create.this,"Account created Successfully",Toast.LENGTH_SHORT).show();

                    }else
                        progressDialog.dismiss();
                    Toast.makeText(create.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                }
            });
    }
}