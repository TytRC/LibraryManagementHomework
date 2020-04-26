package com.example.librarytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.librarytest.support.Book;
import com.example.librarytest.support.Controller;
import com.example.librarytest.support.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Library library = Controller.getLibrary();
    private List<Book> bookList;
    private List<Book> copyBookList;
    private BookAdapter adapter;

    void initBookList(){
        recyclerView = findViewById(R.id.main_view);
        Set<Book> bookSet =  library.getCollectionNumber().keySet();
        bookList = new ArrayList<>(bookSet);
        copyBookList = new ArrayList<>(bookSet);
        adapter = new BookAdapter(bookList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initBookList();
        searchView = findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                doSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                bookList = new ArrayList<>(copyBookList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void doSearch(CharSequence s){
        String msg = s.toString();
        List books = library.query(msg);
        Log.d(TAG, "doSearch: " + books.size() + books);
        bookList.clear();
        bookList.addAll(books);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                Intent intent = new Intent(MainActivity.this, AddBook.class);
                startActivityForResult(intent,1);
                break;
            case R.id.about:
                Toast.makeText(this, "Nothing About us LOL.", Toast.LENGTH_SHORT).show();
                break;
                default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    Book book = (Book) data.getSerializableExtra("book_add");
                    if (book == null){
                        Toast.makeText(this,"添加图书失败",Toast.LENGTH_SHORT).show();
                        break;
                    }
                    bookList.add(book);
                    Log.d(TAG, "onActivityResult: RESULT OK");
                    adapter.notifyItemInserted(bookList.size() - 1);
                }
                break;
        }
    }
}
