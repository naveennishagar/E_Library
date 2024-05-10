package com.example.e_library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ManagebranchActivity extends AppCompatActivity {

    private Button button;
    private DBManager dbManager;

    private EditText editText1, editText2, editText3;
    private Button addbranch, clearbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_branch);

        button = findViewById(R.id.btnback5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminMenu();
            }
        });

        dbManager = new DBManager(this);
        dbManager.open();

        addbranch = findViewById(R.id.btnBranchAdd);
        clearbranch = findViewById(R.id.btnBrachDelete);
        editText1 = findViewById(R.id.txtBrachId);
        editText2 = findViewById(R.id.txtBrachName);
        editText3 = findViewById(R.id.txtbranchadd);

        addbranch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()) {
                    insertBranch();
                }
            }
        });

        clearbranch.setOnClickListener(new View.OnClickListener() {
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

    private void insertBranch() {
        try {
            String branchID = editText1.getText().toString();
            String branchName = editText2.getText().toString();
            String branchAddress = editText3.getText().toString();

            dbManager.insert("insert into Branch values('" + branchID + "','" + branchName + "','" +
                    branchAddress + "')");
            Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
            Log.e("first", "Inserted");
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error in Branch Adding", Toast.LENGTH_SHORT).show();
            Log.e("error", "Error in Branch Adding: " + e.getMessage());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.close();
    }
}
