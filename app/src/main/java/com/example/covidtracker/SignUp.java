package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.text.BreakIterator;

public class SignUp extends AppCompatActivity {

    private EditText etEmail,etPassword;
    private TextView tvAlready;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    SwipeButton swipeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),Menu.class));
        }

        etEmail =(EditText) findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPass);
        tvAlready = (TextView)findViewById(R.id.already);
        swipeButton = findViewById(R.id.swipe_1);

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {
               registerUser();
            }
        });

        tvAlready.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });

    }

    public void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(SignUp.this, "Enter your email", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(email.matches(emailPattern)) {
                Toast.makeText(getApplicationContext(),"WELCOME",Toast.LENGTH_SHORT).show();}
            else{
                Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
            }

        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(SignUp.this, "Enter your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(SignUp.this, "Password must contain 6 elements", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), Menu.class));
                        } else {
                            Toast.makeText(SignUp.this, "ACCOUNT EITHER EXISTS OR SOME ERROR OCCURED", Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}
