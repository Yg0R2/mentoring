package com.epam.mentoring;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.domain.UserRole;
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
    @Autowired
    private UserRestController userRestController;

    public void populateBooksAndAuthors() {
        AuthorResponse authorA = createAuthor("Author", "A");
        AuthorResponse authorB = createAuthor("Author", "B");
        AuthorResponse authorC = createAuthor("Author", "C");

        BookResponse bookA = createBook("Book A", Arrays.asList(authorA, authorB));
        BookResponse bookB = createBook("Book B", Arrays.asList(authorA, authorC));
        BookResponse bookC = createBook("Book C", Arrays.asList(authorC));
    }

    public void populateUsers() {
        UserResponse userA = createUser("User", "A", UserRole.USER);
        UserResponse userB = createUser("User", "B", UserRole.ADMIN);
        UserResponse userC = createUser("User", "B", UserRole.LIBRARIAN);
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

    private UserResponse createUser(String firstName, String lastName, UserRole userRole) {
        UserRequest request = new UserRequest();

        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setUserRole(userRole);

        return userRestController.createUser(request);
    }

}
