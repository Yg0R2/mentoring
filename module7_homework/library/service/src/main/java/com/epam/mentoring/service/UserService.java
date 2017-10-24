package com.epam.mentoring.service;

import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.repository.UserRepository;
import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDAO createUser(UserDAO user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(long id) {
        try {
            userRepository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new NoSuchEntryException("User doesn't exist with the requested id=" + id);
        }
    }

    public UserDAO getUserByEmailAddress(String emailAddress) {
        UserDAO user = userRepository.findByEmailAddress(emailAddress);

        if (user == null) {
            throw new NoSuchEntryException("User doesn't exist with the requested email address=" + emailAddress);
        }

        return user;
    }

    public UserDAO getUserById(long id) {
        UserDAO user = userRepository.findById(id);

        if (user == null) {
            throw new NoSuchEntryException("User doesn't exist with the requested id=" + id);
        }

        return user;
    }

    public List<UserDAO> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public UserDAO updateUser(UserDAO user) {
        UserDAO storedUser = getUserById(user.getId());

        storedUser.setActive(user.isActive());

        if (Objects.nonNull(user.getFirstName())) {
            storedUser.setFirstName(user.getFirstName());
        }

        if (Objects.nonNull(user.getLastName())) {
            storedUser.setLastName(user.getLastName());
        }

        if (Objects.nonNull(user.getEmailAddress())) {
            storedUser.setEmailAddress(user.getEmailAddress());
        }

        if (Objects.nonNull(user.getUserRole())) {
            storedUser.setUserRole(user.getUserRole());
        }

        return storedUser;
    }

}
