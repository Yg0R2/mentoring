package com.epam.training.restapi.controller;

import com.epam.training.restapi.exception.BadUserRequestException;
import com.epam.training.restapi.exception.DuplicateUserException;
import com.epam.training.restapi.exception.UserNotFoundException;
import com.epam.training.restapi.model.User;
import com.epam.training.restapi.repository.UserRepository;
import com.epam.training.restapi.utils.ResponseUtils;
import com.epam.training.restapi.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private Gson gson;
    @Autowired
    private UserRepository repository;

    @PutMapping(path = "", produces = "application/user.api.v1+json")
    public String createUser(@RequestParam String firstName, @RequestParam String lastName) {
        String emailAddress = firstName + "_" + lastName + "@epam.com";

        return createUser(firstName, lastName, emailAddress);
    }

    @PutMapping(path = "", produces = "application/user.api.v2+json")
    @Transactional
    public String createUser(
        @RequestParam String firstName, @RequestParam String lastName, @RequestParam String emailAddress) {

        User user = repository.findByFirstNameAndLastName(firstName, lastName);

        if (user != null) {
            throw new DuplicateUserException();
        }

        user = new User(firstName, lastName, emailAddress);
        user = repository.save(user);

        return gson.toJson(user);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteUser(@PathVariable long id) {
        try {
            repository.delete(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }

        return ResponseUtils.getResponseBody(HttpStatus.OK, null);
    }

    @PostMapping(path = "/{id}")
    @Transactional
    public String editUser(
        @PathVariable long id,
        @RequestParam(required = false) String firstName,
        @RequestParam(required = false) String lastName) {

        if (StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)) {
            throw new BadUserRequestException();
        }

        User user = getValidUserFromRepository(id);

        if (StringUtils.isNotEmpty(firstName)) {
            user.setFirstName(firstName);
        }

        if (StringUtils.isNotEmpty(lastName)) {
            user.setLastName(lastName);
        }

        user = repository.save(user);

        return gson.toJson(user);
    }

    @GetMapping(path = "")
    public @ResponseBody String getUsers() {
        Iterable<User> uersIterable = repository.findAll();

        return gson.toJson(uersIterable);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody String getUser(@PathVariable long id) {
        User user = getValidUserFromRepository(id);

        return gson.toJson(user);
    }

    private User getValidUserFromRepository(final long id) {
        User user = repository.findById(id);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

}
