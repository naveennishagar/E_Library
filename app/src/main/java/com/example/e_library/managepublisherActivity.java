package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class managepublisherActivity extends AppCompatActivity {

    private DBManager dbManager;

    private EditText editText1, editText2, editText3;
    private Button addpublish, clearpub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_publisher);

        dbManager = new DBManager(this);
        dbManager.open();

        editText1 = findViewById(R.id.txtPubName);
        editText2 = findViewById(R.id.txtpublisheradd);
        editText3 = findViewById(R.id.txtpubcontact);

        Button backButton = findViewById(R.id.btnback4);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        addpublish = findViewById(R.id.btnaddpub);
        addpublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertPublisher();
                }
            }
        });

        clearpub = findViewById(R.id.btndeletepub);
        clearpub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearFields();
            }
        });
    }

    private boolean validateFields() {
        if (isEmpty(editText1) || isEmpty(editText2) || isEmpty(editText3)) {
            Toast.makeText(getApplicationContext(), "Fields can't be null", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private void insertPublisher() {
        try {
            String name = editText1.getText().toString();
            String address = editText2.getText().toString();
            String phone = editText3.getText().toString();

            dbManager.insert("insert into Publisher values('" + name + "','" + address + "','" + phone + "')");
            Toast.makeText(managepublisherActivity.this, "Successfully Inserted", Toast.LENGTH_SHORT).show();
            Log.e("first", "Inserted");
        } catch (Exception e) {
            Toast.makeText(managepublisherActivity.this, "Error in Publisher Adding", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Publisher Adding: " + e.getMessage());
        }
    }

    private void clearFields() {
        editText1.getText().clear();
        editText2.getText().clear();
        editText3.getText().clear();
        Toast.makeText(getApplicationContext(), "Your Successfully Deleted", Toast.LENGTH_SHORT).show();
    }

    public void adminMenu() {
        Intent intent = new Intent(this, AdminmenuActivity.class);
        startActivity(intent);
    }
}
