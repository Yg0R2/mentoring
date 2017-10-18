package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.exception.NoSuchEntryException;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.UserResponse;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserRestController {

    private static final Type USER_RESPONSE_LIST_TYPE = new TypeToken<List<UserResponse>>() {}.getType();
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UserRequest: {}", gson.toJson(userRequest));
        }

        UserDAO user = new UserDAO(userRequest.getFirstName(), userRequest.getLastName());

        user.setUserRole(userRequest.getUserRole());

        return mapToResponse(userService.createUser(user));
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

        UserDAO user = userService.getUserById(id);

        if (user == null) {
            throw new NoSuchEntryException("User doesn't exist with the requested id=" + id);
        }

        return mapToResponse(user);
    }

    @GetMapping(path = "/users")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getUsers() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all users.");
        }

        return mapToResponse(userService.getUsers());
    }

    private UserResponse mapToResponse(UserDAO user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UserResponse: {}", gson.toJson(userResponse));
        }

        return userResponse;
    }

    private List<UserResponse> mapToResponse(List<UserDAO> users) {
        List<UserResponse> userResponses = modelMapper.map(users, USER_RESPONSE_LIST_TYPE);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("List<UserResponse>: {}", gson.toJson(userResponses));
        }

        return userResponses;
    }

}
