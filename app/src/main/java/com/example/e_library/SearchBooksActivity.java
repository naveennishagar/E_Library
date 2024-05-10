package com.example.e_library;

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

public class SearchBooksActivity extends AppCompatActivity {

    private DatabaseHelper db;
    private ListView userList;
    private ArrayList<String> listItem;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_books);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Search Books");
        }

        db = new DatabaseHelper(this);
        listItem = new ArrayList<>();
        userList = findViewById(R.id.users_list);

        viewData();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = userList.getItemAtPosition(position).toString();
                Intent intent = new Intent(SearchBooksActivity.this, memberMenu.class);
                intent.putExtra("BookInfo", text);
                startActivity(intent);
                Toast.makeText(SearchBooksActivity.this, "" + text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = db.getAllBooks();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "NO DATA TO SHOW", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                listItem.add("Book Name: " + cursor.getString(1) + '\n' +
                        "Author: " + cursor.getString(3) + '\n' +
                        "Branch: " + cursor.getString(4));
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
