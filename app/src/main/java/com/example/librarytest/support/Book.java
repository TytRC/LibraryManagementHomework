package com.example.librarytest.support;

import java.io.Serializable;
import java.util.*;

/**
 * This class is used to described a book,
 * which means the instance of the class is a book
 * that is different from any other.
 * Here comes two situation : to describe a book,
 * @author: Rain Chen
 *
 */

public class Book implements Serializable {
    private static final Map<Integer, Book> idToBook = new HashMap<>();
    private static final Map<Book, List<Book>> bookMap = new HashMap<>();
    private static int cnt = 0;
    private static int kinds = 0;
    private final String bookName;
    private final List<String> author;
    private final String press;
    private final int id;
    private final int kind;

    public Book(String bookName, String[] authors, String press) {
        this.bookName = bookName;
        this.author = authors == null ? null : Arrays.asList(authors);
        this.press = press;
        id = ++cnt;
        idToBook.put(id, this);
        if (!bookMap.containsKey(this)){
            this.kind = ++kinds;
            bookMap.put(this, new ArrayList<Book>());
        }else this.kind = kinds;
        bookMap.get(this).add(this);
    }

    public static Book findBookById(int id){
        return idToBook.get(id);
    }

    public static Book[] findBooksByBook(Book book){
        return (Book[]) bookMap.get(book).toArray();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + (bookName == null ? 0 : bookName.hashCode());
        result = result * 31 + (author == null ? 0 : author.hashCode());
        result = result * 31 + (press == null ? 0 : press.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (! (obj instanceof Book)) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        Book book = (Book) obj;
        if (author == null && book.author != null)
            return false;
        return equalString(this.press, book.press) && author.equals(book.author)
                && equalString(this.bookName, book.bookName);
    }

    private boolean equalString(String s1, String s2){
        if (s1 == null)
            return s2 == null;
        return s1.equals(s2);
    }

    @Override
    public String toString() {
        return "书名: \t" + bookName +
                "作者: \t" + author +
                "出版社: \t" + press +
                "id: \t" + id;
    }

    public String getBookName() {
        return bookName;
    }

    public String[] getAuthor() {
        return author == null ? null : (String[]) author.toArray();
    }

    public String getPress() {
        return press;
    }

    public int getId() {
        return id;
    }

    public int getKind(){
        return kind;
    }

    public static Map<Book, List<Book>> getBookMap() {
        return bookMap;
    }
}
