package com.epam.mentoring;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.api.utils.ModelMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DummyBookDataPopulator {

    @Autowired
    private AuthorRestController authorRestController;
    @Autowired
    private BookRestController bookRestController;
    @Autowired
    private ModelMapperUtils modelMapperUtils;

    public void populateBooks() {
        AuthorResponse authorA = authorRestController.getAuthor(1);
        AuthorResponse authorB = authorRestController.getAuthor(2);
        AuthorResponse authorC = authorRestController.getAuthor(3);

        createBook("Book A", Arrays.asList(authorA, authorB));
        createBook("Book B", Arrays.asList(authorA, authorC));
        createBook("Book C", Arrays.asList(authorB));
    }

    private void createBook(String title, List<AuthorResponse> authors) {
        BookRequest bookRequest = new BookRequest();

        bookRequest.setTitle(title);
        bookRequest.setAuthors(modelMapperUtils.map(authors, ModelMapperUtils.AUTHOR_REQUEST_LIST_TYPE));

        bookRestController.createBook(bookRequest);
    }


}
