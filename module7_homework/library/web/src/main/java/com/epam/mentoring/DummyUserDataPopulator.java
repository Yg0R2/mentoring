package com.epam.mentoring;

import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyUserDataPopulator {

    @Autowired
    private UserRestController userRestController;

    public void populateUsers() {
        createUser("User", "A", UserRole.USER);
        createUser("User", "B", UserRole.ADMIN);
        createUser("User", "B", UserRole.LIBRARIAN);
    }

    private void createUser(String firstName, String lastName, UserRole userRole) {
        UserRequest userRequest = new UserRequest();

        userRequest.setFirstName(firstName);
        userRequest.setLastName(lastName);
        userRequest.setUserRole(userRole);

        userRestController.createUser(userRequest);
    }

}
