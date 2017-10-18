package com.epam.mentoring;

import com.epam.mentoring.domain.AuthorDAO;
import com.epam.mentoring.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyAuthorDataPopulator {

    @Autowired
    private AuthorService authorService;

    public void populateAuthors() {
        createAuthor("Author", "A");
        createAuthor("Author", "B");
        createAuthor("Author", "C");
    }

    private void createAuthor(String firstName, String lastName) {
        AuthorDAO author = new AuthorDAO(firstName, lastName);

        authorService.createAuthor(author);
    }


}
