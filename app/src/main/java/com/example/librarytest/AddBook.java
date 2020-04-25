package com.example.librarytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarytest.support.Book;
import com.example.librarytest.support.Controller;
import com.example.librarytest.support.Library;

import org.w3c.dom.Text;

public class AddBook extends AppCompatActivity {

    private TextView bookName;
    private TextView bookAuthor;
    private TextView bookNum;
    private TextView bookPress;
    private Button cancel;
    private Button ok;
    private Library library;

    private void init(){
        library = Controller.getLibrary();
        bookName = findViewById(R.id.name_msg);
        bookAuthor = findViewById(R.id.author_msg);
        bookNum = findViewById(R.id.num_msg);
        bookPress = findViewById(R.id.press_msg);
        cancel = findViewById(R.id.cancel);
        ok = findViewById(R.id.ok);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = bookName.getText().toString();
                String[] author = bookAuthor.getText().toString().split(",");
                String press = bookPress.getText().toString();
                int num = Integer.parseInt(bookNum.getText().toString());
                if (num <= 0){
                    Toast.makeText(AddBook.this,"书的数量必须大于一",Toast.LENGTH_SHORT).show();
                }else{
                    Book[] books = new Book[num];
                    for (int i = 0; i < num; i++){
                        books[i] = new Book(name, author, press);
                    }
                    library.addBooks(books);
                    Intent intent = new Intent();
                    intent.putExtra("book_add", books[0]);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }
}
