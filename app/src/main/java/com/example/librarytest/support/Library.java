package com.example.librarytest.support;

import android.annotation.SuppressLint;

import java.util.*;

public class Library {
    private final Map<Book, Integer> collectionNumber = new HashMap<>();
    private final Map<Book, Integer> borrowingNumber = new HashMap<>();
    @SuppressLint("UseSparseArrays")
    private final Map<Integer, Borrower> bookToBorrower = new HashMap<>();

    public Boolean addBook(Book book){
        int id = book.getId();
        if (bookToBorrower.containsKey(id))
            return false;
        bookToBorrower.put(id, null);
        if (collectionNumber.containsKey(book))
            collectionNumber.put(book, 1 + collectionNumber.get(book));
        else {
            collectionNumber.put(book, 1);
            borrowingNumber.put(book, 0);
        }
        return true;
    }

    public Boolean[] addBooks(Book[] books){
        if (books == null)
            return null;
        Boolean[] results = new Boolean[books.length];
        for (int i = 0;i < books.length;i++)
            results[i] = addBook(books[i]);
        return results;
    }

    public Boolean deleteBook(Book book){
        int id = book.getId();
        if (!bookToBorrower.containsKey(id) || bookToBorrower.get(id) != null)
            return false;
        collectionNumber.put(book, collectionNumber.get(book) - 1);
        bookToBorrower.remove(id);
        return true;
    }

    public Boolean deleteBooks(Book book){
        if (collectionNumber.containsKey(book) && borrowingNumber.get(book) == 0){
            collectionNumber.remove(book);
            borrowingNumber.remove(book);
            for (Book i : Book.findBooksByBook(book)){
                bookToBorrower.remove(i.getId());
            }
            return true;
        }
        return false;
    }

    public Boolean lendBook(Book book, Borrower borrower){
        int id = book.getId();
        //如果图书馆没有这本书或者已经被借出去了，返回false
        if (!bookToBorrower.containsKey(id) || bookToBorrower.get(id) != null)
            return false;
        if (! borrower.borrowBook(book))//如果借阅者借阅不成功(书满了，或者已经有了，返回false
            return false;
        borrowingNumber.put(book, borrowingNumber.get(book) + 1);
        return true;
    }

    public Boolean returnBook(Book book, Borrower borrower){
        int id = book.getId();
        //如果没有这本书，或者这本书没有被借出去，则返回失败
        if (bookToBorrower.get(id) == null)
            return false;
        //如果借阅者返还不成功，即借阅者没有借过这本书，则返回失败
        if (!borrower.returnBook(book))
            return false;
        borrowingNumber.put(book, borrowingNumber.get(book) - 1);
        return false;
    }

    public List<Book> query(String msg){
        System.out.println(msg);
        ArrayList<Book> result = new ArrayList<>();
        Iterator<Book> bi = collectionNumber.keySet().iterator();
        Book book;
        while (bi.hasNext()){
            book = bi.next();
            if (book.getBookName().contains(msg))
                result.add(book);
        }
        return result;
    }

    public int[] query(Book book){
        if (book == null)
            return null;
        if (collectionNumber.get(book) == null || borrowingNumber.get(book) == null)
            return null;
        if (!collectionNumber.containsKey(book))
            return null;
        return new int[]{collectionNumber.get(book), borrowingNumber.get(book)};
    }

    public Map<Book, Integer> getCollectionNumber() {
        return collectionNumber;
    }

    public Map<Book, Integer> getBorrowingNumber() {
        return borrowingNumber;
    }

    public Map<Integer, Borrower> getBookToBorrower() {
        return bookToBorrower;
    }
}
