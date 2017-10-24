package com.epam.mentoring;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.request.AuthorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyAuthorDataPopulator {

    @Autowired
    private AuthorRestController authorRestController;

    public void populateAuthors() {
        createAuthor("Author", "A");
        createAuthor("Author", "B");
        createAuthor("Author", "C");
    }

    private void createAuthor(String firstName, String lastName) {
        AuthorRequest authorRequest = new AuthorRequest();

        authorRequest.setFirstName(firstName);
        authorRequest.setLastName(lastName);

        authorRestController.createAuthor(authorRequest);
    }


}
