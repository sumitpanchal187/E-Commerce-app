package com.example.ecommerce.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth ;
    private EditText loginEmail,loginPassword ;
    private TextView signUpRedirectedText ;

    private Button loginButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        loginEmail = findViewById(R.id.loginemail);

        loginPassword = findViewById(R.id.loginpass);

        loginButton = findViewById(R.id.loginbutton);

        signUpRedirectedText = findViewById(R.id.newuser);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = loginEmail.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();

                if (user.isEmpty() ){
                    loginEmail.setError("Email cannot not be empty");
                }
                if (password.isEmpty() ){
                    loginPassword.setError("Password cannot not be empty");
                }
                else {
                    auth.signInWithEmailAndPassword(user,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, WelcomeActivity    .class));
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        signUpRedirectedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}