package com.epam.mentoring.service;

import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public UserDAO createUser(UserDAO user) {
        return userRepository.save(user);
    }

    public UserDAO getUserById(long id) {
        return userRepository.findById(id);
    }

    public List<UserDAO> getUsers() {
        return userRepository.findAll();
    }

}
