package com.example.e_library;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MemberActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ListView userList;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Search Member");
        }

        db = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        userList = findViewById(R.id.users_list);

        viewData();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userList.getItemAtPosition(position).toString();
                Intent intent = new Intent(MemberActivity.this, memberMenu.class);
                intent.putExtra("MemberInfo", text);
                startActivity(intent);
                Toast.makeText(MemberActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("Range")
    private void viewData() {
        Cursor cursor = db.getAllMembers(); // Fetch member details from the database

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA TO SHOW", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add("First Name: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_FIRST_NAME)) + '\n' +
                        "Last Name: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_LAST_NAME)) + '\n' +
                        "Username: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_USERNAME)) + '\n' +
                        "Email: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_EMAIL)) + '\n' +
                        "Phone: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_CONTACT)) + '\n' +
                        "Password: " + cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PASSWORD)));
            }
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
            userList.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> filteredList = new ArrayList<>();
                for (String item : listItem) {
                    if (item.toLowerCase().contains(newText.toLowerCase())) {
                        filteredList.add(item);
                    }
                }
                adapter.clear();
                adapter.addAll(filteredList);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
        return true;
    }
}
