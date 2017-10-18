package com.epam.mentoring;

import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.domain.BookDAO;
import com.epam.mentoring.service.AuthorService;
import com.epam.mentoring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DummyBookDataPopulator {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    public void populateBooks() {
        AuthorDAO authorA = authorService.getAuthorById(1);
        AuthorDAO authorB = authorService.getAuthorById(2);
        AuthorDAO authorC = authorService.getAuthorById(3);

        createBook("Book A", Arrays.asList(authorA, authorB));
        createBook("Book B", Arrays.asList(authorA, authorC));
        createBook("Book C", Arrays.asList(authorB));
    }

    private void createBook(String title, List<AuthorDAO> authors) {
        BookDAO book = new BookDAO(title);

        book.setAuthors(authors);

        bookService.createBook(book);
    }


}
