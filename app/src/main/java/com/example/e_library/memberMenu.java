package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class memberMenu extends AppCompatActivity {

    Button logoutButton;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_menu);

        logoutButton = findViewById(R.id.btnmemberlogout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        searchButton = findViewById(R.id.btnserachbook);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchBooksActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }

    private void goToSearchBooksActivity() {
        Intent intent = new Intent(this, SearchBooksActivity.class);
        startActivity(intent);
    }
}
