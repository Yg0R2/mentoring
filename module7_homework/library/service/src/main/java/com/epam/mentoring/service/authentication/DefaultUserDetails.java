package com.epam.mentoring.service.authentication;

import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.domain.UserRole;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class DefaultUserDetails extends User {

    private UserDAO user;

    public DefaultUserDetails(UserDAO user) {
        super(user.getEmailAddress(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserRole().toString()));

        this.user = user;
    }

    public UserDAO getUser() {
        return user;
    }

    public long getId() {
        return user.getId();
    }

    public UserRole getRole() {
        return user.getUserRole();
    }

}
