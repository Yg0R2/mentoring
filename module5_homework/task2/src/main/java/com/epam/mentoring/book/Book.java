package com.epam.mentoring.book;

import com.epam.mentoring.author.Author;
import com.google.gson.Gson;

import java.util.List;
import java.util.stream.Collectors;

public class Book {

    private String title;
    private List<Author> authors;
    private int numberOfPages;

    public Book(String title, List<Author> authors, int numberOfPages) {
        this.title = title;
        this.authors = authors;
        this.numberOfPages = numberOfPages;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    @Override
    public String toString() {
        String authorNames = authors.stream()
            .map(Author::getName).collect(Collectors.joining(", "));

        return "Book: {Title: " + title + ", Authors: {" + authorNames + "}, Number of pages: " + numberOfPages + "} ";
    }

}
