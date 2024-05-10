package com.example.e_library;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookLendingActivity extends AppCompatActivity {

    private TextView textViewBookLending;
    private DBManager dbManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_lending);

        textViewBookLending = findViewById(R.id.textViewBookLending);
        dbManager = new DBManager(this);

        // Fetch book lending details from the database and display them
        displayBookLendingDetails();
    }

    private void displayBookLendingDetails() {
        StringBuilder lendingDetails = new StringBuilder();

        // Assuming you have a method in DBManager to fetch book lending details
        Cursor cursor = dbManager.select("SELECT * FROM BookLoan");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String branchID = cursor.getString(cursor.getColumnIndex("BranchID"));
                String cardNo = cursor.getString(cursor.getColumnIndex("CardNo"));
                String dateOut = cursor.getString(cursor.getColumnIndex("DateOut"));
                String dateDue = cursor.getString(cursor.getColumnIndex("DateDue"));
                String dateReturned = cursor.getString(cursor.getColumnIndex("DateReturned"));

                lendingDetails.append("Branch ID: ").append(branchID).append("\n");
                lendingDetails.append("Card No: ").append(cardNo).append("\n");
                lendingDetails.append("Date Out: ").append(dateOut).append("\n");
                lendingDetails.append("Date Due: ").append(dateDue).append("\n");
                lendingDetails.append("Date Returned: ").append(dateReturned).append("\n\n");
            }
        } else {
            lendingDetails.append("No book lending details found.");
        }

        // Display the book lending details in the TextView
        textViewBookLending.setText(lendingDetails.toString());

        // Close the cursor
        if (cursor != null) {
            cursor.close();
        }
    }
}
