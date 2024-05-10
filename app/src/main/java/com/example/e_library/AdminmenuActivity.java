package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity; // Import the correct AppCompatActivity

public class AdminmenuActivity extends AppCompatActivity {

    private Button logoutBtn, addingBtn, managePubBtn, branchBtn, memberBtn,ViewBookLendingBtn, lendingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);

        logoutBtn = findViewById(R.id.btnlogout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity(); // Call the method properly
            }
        });

        addingBtn = findViewById(R.id.btnaddingB);
        addingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageBooks();
            }
        });

        managePubBtn = findViewById(R.id.btnmanagepub);
        managePubBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managePublisher();
            }
        });

        branchBtn = findViewById(R.id.btnbranch);
        branchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manageBranch();
            }
        });
        memberBtn = findViewById(R.id.btnmember);
        memberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMember();
            }
        });

        ViewBookLendingBtn = findViewById(R.id.btnViewBookLending);
        ViewBookLendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBookLending();
            }
        });


        lendingBtn = findViewById(R.id.btnlending);
        lendingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lending();
            }
        });

    }

    public void manageBooks(){
        Intent intent = new Intent(this, ManagebooksActivity.class);
        startActivity(intent);
    }

    public void managePublisher(){
        Intent intent = new Intent(this, managepublisherActivity.class);
        startActivity(intent);
    }

    public void manageBranch(){
        Intent intent = new Intent(this, ManagebranchActivity.class);
        startActivity(intent);
    }

    public void viewMember(){
        Intent intent = new Intent(this, MemberActivity.class);
        startActivity(intent);
    }

    public void viewBookLending(){
        Intent intent = new Intent(this, BookLendingActivity.class);
        startActivity(intent);
    }

    public void lending(){
        Intent intent = new Intent(this, LendingActivity.class);
        startActivity(intent);
    }

    public void MainActivity(){ // Correct method name to follow Java naming convention
        Intent intent = new Intent(this, WelcomeActivity.class);
        startActivity(intent);
    }
}
