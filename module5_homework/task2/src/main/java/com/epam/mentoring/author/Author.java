package com.epam.mentoring.author;

import com.epam.mentoring.book.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Author {

    private String name;
    private short age;
    private List<Book> books;

    public Author(String name, short age) {
        this.name = name;
        this.age = age;
        books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public short getAge() {
        return age;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBook(List<Book> books) {
        this.books = books;
    }

}
