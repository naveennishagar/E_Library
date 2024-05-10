package com.example.e_library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagebooksActivity extends AppCompatActivity {

    private Button button;
    private DBManager dbManager;
    private DatabaseHelper dbHelper;

    private EditText editText1, editText2, editText3, editText4, editText5;
    private Button addbook, clear, updateButton, deleteButton,viewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);

        button = findViewById(R.id.btnback3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();
        dbHelper = new DatabaseHelper(this);

        addbook = findViewById(R.id.btnbookadd);
        clear = findViewById(R.id.btndelete);
        editText1 = findViewById(R.id.txtBookID);
        editText2 = findViewById(R.id.txtBooktitle);
        editText3 = findViewById(R.id.txtpublishername);
        editText4 = findViewById(R.id.txtauthorname2);
        editText5 = findViewById(R.id.txtaddbranchname);
        updateButton = findViewById(R.id.btnUpdate);
        deleteButton = findViewById(R.id.btnDelete);
        viewButton=findViewById(R.id.btnview);

        addbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertBook();
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    updateBook();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBook();
            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewBook();
            }
        });

        // Populate fields when BookID loses focus
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String bookID = editText1.getText().toString().trim();
                    if (!bookID.isEmpty()) {
                        populateFields(bookID);
                    }
                }
            }
        });
    }

    private boolean validateFields() {
        if (isEmpty(editText1) || isEmpty(editText2) || isEmpty(editText3) || isEmpty(editText4) || isEmpty(editText5)) {
            Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void insertBook() {
        try {
            String bookID = editText1.getText().toString();
            String bookName = editText2.getText().toString();
            String bookPublisher = editText3.getText().toString();
            String bookAuthor = editText4.getText().toString();
            String branch = editText5.getText().toString();

            dbManager.insert("insert into " + DatabaseHelper.TABLE_NAME_BOOK + " values('" + bookID + "','" + bookName + "','" +
                    bookPublisher + "','" + bookAuthor + "','" + branch + "')");
            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
            Log.e("first", "Inserted");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in Book Adding", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Book Adding: " + e.getMessage());
        }
    }

    private void updateBook() {
        try {
            String bookID = editText1.getText().toString();
            String bookName = editText2.getText().toString();
            String bookPublisher = editText3.getText().toString();
            String bookAuthor = editText4.getText().toString();
            String branch = editText5.getText().toString();

            dbManager.update("UPDATE " + DatabaseHelper.TABLE_NAME_BOOK + " SET " + DatabaseHelper.COL_BOOK_NAME + " = '" + bookName + "', " +
                    DatabaseHelper.COL_BOOK_PUBLISHER + " = '" + bookPublisher + "', " + DatabaseHelper.COL_BOOK_AUTHOR + " = '" + bookAuthor + "', " +
                    DatabaseHelper.COL_BRANCH + " = '" + branch + "' WHERE " + DatabaseHelper.COL_BOOK_ID + " = '" + bookID + "'");
            Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            Log.e("first", "Updated");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in Book Updating", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Book Updating: " + e.getMessage());
        }
    }

    private void deleteBook() {
        try {
            String bookID = editText1.getText().toString();

            dbManager.delete("DELETE FROM " + DatabaseHelper.TABLE_NAME_BOOK + " WHERE " + DatabaseHelper.COL_BOOK_ID + " = '" + bookID + "'");
            Toast.makeText(getApplicationContext(), "Successfully Deleted", Toast.LENGTH_SHORT).show();
            Log.e("first", "Deleted");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in Book Deleting", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Book Deleting: " + e.getMessage());
        }
    }

    private void clearFields() {
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        editText4.getText().clear();
        editText5.getText().clear();
        Toast.makeText(getApplicationContext(), "Your Successfully Deleted", Toast.LENGTH_SHORT).show();
    }

    private void adminMenu() {
        Intent intent = new Intent(this, AdminmenuActivity.class);
        startActivity(intent);
    }

    private void viewBook() {
        Intent intent = new Intent(this, SearchBooksActivity.class);
        startActivity(intent);
    }

    private void populateFields(String bookID) {
        Cursor cursor = dbHelper.searchUsers(bookID);
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String bookName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BOOK_NAME));
            @SuppressLint("Range") String bookPublisher = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BOOK_PUBLISHER));
            @SuppressLint("Range") String bookAuthor = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BOOK_AUTHOR));
            @SuppressLint("Range") String branch = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_BRANCH));

            editText2.setText(bookName);
            editText3.setText(bookPublisher);
            editText4.setText(bookAuthor);
            editText5.setText(branch);

            cursor.close();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
