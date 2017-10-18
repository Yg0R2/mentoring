package com.epam.mentoring;

import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.domain.UserRole;
import com.epam.mentoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DummyUserDataPopulator {

    @Autowired
    private UserService userService;

    public void populateUsers() {
        createUser("User", "A", UserRole.USER);
        createUser("User", "B", UserRole.ADMIN);
        createUser("User", "B", UserRole.LIBRARIAN);
    }

    private void createUser(String firstName, String lastName, UserRole userRole) {
        UserDAO user = new UserDAO(firstName, lastName);

        user.setUserRole(userRole);

        userService.createUser(user);
    }

}
