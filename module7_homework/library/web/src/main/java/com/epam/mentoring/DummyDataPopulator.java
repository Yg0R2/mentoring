package com.epam.mentoring;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.api.response.BookResponse;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Component
public class DummyDataPopulator {

    private static final Type AUTHOR_REQUESTS_TYPE = new TypeToken<List<AuthorRequest>>() {}.getType();

    @Autowired
    private AuthorRestController authorRestController;
    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private ModelMapper modelMapper;

    public void populateBooksAndAuthors() {
        AuthorResponse authorA = createAuthor("Author", "A");
        AuthorResponse authorB = createAuthor("Author", "B");
        AuthorResponse authorC = createAuthor("Author", "C");

        BookResponse bookA = createBook("Book A", Arrays.asList(authorA, authorB));
        BookResponse bookB = createBook("Book B", Arrays.asList(authorA, authorC));
        BookResponse bookC = createBook("Book C", Arrays.asList(authorC));
    }

    private AuthorResponse createAuthor(String firstName, String lastName) {
        AuthorRequest request = new AuthorRequest();

        request.setFirstName(firstName);
        request.setLastName(lastName);

        return authorRestController.createAuthor(request);
    }

    private BookResponse createBook(String title, List<AuthorResponse> authors) {
        BookRequest request = new BookRequest();

        request.setTitle(title);

        if (authors != null) {
            request.setAuthors(modelMapper.map(authors, AUTHOR_REQUESTS_TYPE));
        }

        return bookRestController.createBook(request);
    }

}
