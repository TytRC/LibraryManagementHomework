package com.example.librarytest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarytest.support.Book;
import com.example.librarytest.support.Controller;

import java.util.Arrays;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> mBookList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View bookView;
        ImageView bookImage;
        TextView bookName, bookAuthor, bookDigest, num, borrowNum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookView = itemView;
            bookImage = itemView.findViewById(R.id.book_image);
            bookName = itemView.findViewById(R.id.book_name);
            bookAuthor = itemView.findViewById(R.id.book_author);
            bookDigest = itemView.findViewById(R.id.book_digest);
            num = itemView.findViewById(R.id.sum_num);
            borrowNum = itemView.findViewById(R.id.borrow_num);
        }
    }

    public BookAdapter(List<Book> mBookList) {
        this.mBookList = mBookList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.book_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.bookView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Book book = mBookList.get(position);
                Intent intent = new Intent(parent.getContext(), BookShowActivity.class);
                intent.putExtra("book", book);
                intent.putExtra("sum_num", Controller.getLibrary().getCollectionNumber().get(book));
                intent.putExtra("borrow_num", Controller.getLibrary().getBorrowingNumber().get(book));
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Book book = mBookList.get(position);
        String[] temp = book.getAuthor();
        String bookAuthor = null;
        int[] num = Controller.getLibrary().query(book);
        if (temp != null)
            bookAuthor =  Arrays.toString(temp).replace('[','\0').
                replace(']','\0');
        holder.bookName.setText(book.getBookName());
        holder.bookAuthor.setText("作者：" + bookAuthor);
        holder.num.setText("馆藏数量：" + num[0]);
        holder.borrowNum.setText("借出数量：" + num[1]);
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }




}
