package com.example.librarytest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.librarytest.support.Book;
import com.example.librarytest.support.Controller;
import com.example.librarytest.support.Library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView recyclerView;
    private Library library = Controller.getLibrary();
    private List<Book> bookList;
    private BookAdapter adapter;

    void initBookList(){
        recyclerView = findViewById(R.id.main_view);
        Set<Book> bookSet =  library.getCollectionNumber().keySet();
        bookList = new ArrayList<>(bookSet);
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
