package com.epam.mentoring;

import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.request.UserRequest;
import com.epam.mentoring.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyUserDataPopulator {

    @Autowired
    private UserRestController userRestController;

    public void populateUsers() {
        createUser("User", "A", UserRole.USER);
        createUser("User", "B", UserRole.LIBRARIAN);
        createUser("User", "C", UserRole.ADMIN);
    }

    private void createUser(String firstName, String lastName, UserRole userRole) {
        UserRequest userRequest = new UserRequest();

        userRequest.setActive(true);
        userRequest.setFirstName(firstName);
        userRequest.setLastName(lastName);
        userRequest.setEmailAddress(firstName + "." + lastName + "@library.com");
        userRequest.setPassword(new BCryptPasswordEncoder().encode("test"));
        userRequest.setUserRole(userRole);

        userRestController.createUser(userRequest);
    }

}
