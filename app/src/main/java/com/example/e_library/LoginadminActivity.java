package com.example.e_library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginadminActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button loginButton;
    TextView signup;
    FirebaseDatabase database;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiity_loginadmin);

        username = findViewById(R.id.usernameAdmin);
        password = findViewById(R.id.passwordAdmin);
        loginButton = findViewById(R.id.loginButtonAdmin);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("Admin") && password.getText().toString().equals("ABC1234")) {
                    Toast.makeText(LoginadminActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( LoginadminActivity.this,AdminmenuActivity.class);
                    startActivity(intent);


                } else {
                    Toast.makeText(LoginadminActivity.this, "Login failed", Toast.LENGTH_SHORT).show(); // Corrected typo
                }
            }
        });


    }
}
