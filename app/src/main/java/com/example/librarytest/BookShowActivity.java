package com.example.librarytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.librarytest.support.Book;
import com.example.librarytest.support.Controller;
import com.example.librarytest.support.Library;

import java.util.Arrays;

public class BookShowActivity extends AppCompatActivity {

    private TextView bookName;
    private TextView bookAuthor;
    private TextView bookPress;
    private TextView bookDigest;
    private TextView bookSumNum;
    private TextView bookNum;
    private Library library;
    Intent intent;

    private void init(Book book){
        library = Controller.getLibrary();
        bookName = findViewById(R.id.book_name);
        bookAuthor = findViewById(R.id.book_author);
        bookDigest = findViewById(R.id.book_digest);
        bookPress = findViewById(R.id.book_press);
        bookSumNum = findViewById(R.id.sum_num);
        bookNum = findViewById(R.id.borrow_num);
        bookName.setText(book.getBookName());
        bookAuthor.setText(Arrays.toString(book.getAuthor()).replace('[','\0').
                replace(']','\0'));
        bookPress.setText(book.getPress());
        int[] num = library.query(book);
        bookSumNum.setText("馆藏数量：" + num[0]);
        bookNum.setText("借出数量：" + num[1]);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_show);
        intent = getIntent();
        Book book = (Book) intent.getSerializableExtra("book");
        if (book != null)
            init(book);
    }
}
