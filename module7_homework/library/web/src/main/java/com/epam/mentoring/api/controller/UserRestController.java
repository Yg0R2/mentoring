package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        UserDAO user = userMapper.mapToDao(userRequest);

        UserDAO storedUser = userService.createUser(user);

        return userMapper.mapToResponse(storedUser);
    }

    @DeleteMapping(path = "/user")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteUser(@RequestParam long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path = "/user")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public UserResponse getUser() {
        throw new MissingRequestParameterException("Missing request parameter.");
    }

    @GetMapping(path = "/user", params = {"id"})
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse getUser(@RequestParam long id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get user by Id: {}", id);
        }

        UserDAO storedUser = userService.getUserById(id);

        return userMapper.mapToResponse(storedUser);
    }

    @GetMapping(path = "/users")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getUsers() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all users.");
        }

        List<UserDAO> storedUsers = userService.getUsers();

        return userMapper.mapToResponse(storedUsers);
    }

    @PutMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse updateUser(@RequestBody @Valid UserRequest userRequest) {
        UserDAO user = userMapper.mapToDao(userRequest);

        UserDAO storedUser = userService.updateUser(user);

        return userMapper.mapToResponse(storedUser);
    }

}
