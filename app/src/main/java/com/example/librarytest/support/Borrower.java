package com.example.librarytest.support;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private final String name;
    private final String studentId;
    private Authority authority;
    private final List<Integer> borrowedBook;
    private int bookNum = 0;

    public List<Book> getBorrowedBook() {
        List<Book> result = new ArrayList<>();
        Book book;
        for (int i : borrowedBook){
            book = Book.findBookById(i);
            result.add(book);
        }
        return result;
    }

    public Borrower(String name, String studentId, Authority authority) {
        this.name = name;
        this.studentId = studentId;
        this.authority = authority;
        this.borrowedBook = new ArrayList<>();
    }

    public Borrower(String name, String studentId){
        this(name, studentId, Authority.getNORMAL());
    }

    public boolean borrowBook(Book book){
        int id = book.getId();
        if (bookNum >= authority.getBookNumber() || borrowedBook.contains(id))
            return false;
        borrowedBook.add(id);
        ++bookNum;
        return true;
    }

    public boolean returnBook(Book book){
        int id = book.getId();
        if (!borrowedBook.contains(id))
            return false;
        borrowedBook.remove(id);
        --bookNum;
        return true;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public Authority getAuthority() {
        return authority;
    }

}
