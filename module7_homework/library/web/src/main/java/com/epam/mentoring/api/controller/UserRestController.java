package com.epam.mentoring.api.controller;

import com.epam.mentoring.api.exception.MissingRequestParameterException;
import com.epam.mentoring.api.utils.ModelMapperUtils;
import com.epam.mentoring.domain.UserDAO;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.UserResponse;
import com.google.gson.Gson;
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
    private UserService userService;
    @Autowired
    private Gson gson;
    @Autowired
    private ModelMapperUtils modelMapperUtils;

    @PostMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        UserDAO user = getUserFromRequest(userRequest);

        return mapToResponse(userService.createUser(user));
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

        return mapToResponse(userService.getUserById(id));
    }

    @GetMapping(path = "/users")
    @ResponseStatus(value = HttpStatus.OK)
    public List<UserResponse> getUsers() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Get all users.");
        }

        return mapToResponse(userService.getUsers());
    }

    @PutMapping(path = "/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public UserResponse updateUser(@RequestBody @Valid UserRequest userRequest) {
        UserDAO user = getUserFromRequest(userRequest);

        return mapToResponse(userService.updateUser(user));
    }

    private UserDAO getUserFromRequest(UserRequest userRequest) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("UserRequest: {}", gson.toJson(userRequest));
        }

        UserDAO user = new UserDAO(userRequest.getFirstName(), userRequest.getLastName());

        user.setId(userRequest.getId());
        user.setUserRole(userRequest.getUserRole());

        return user;
    }

    private UserResponse mapToResponse(UserDAO user) {
        return modelMapperUtils.map(user, ModelMapperUtils.USER_RESPONSE_TYPE);
    }

    private List<UserResponse> mapToResponse(List<UserDAO> users) {
        return modelMapperUtils.map(users, ModelMapperUtils.USER_RESPONSE_LIST_TYPE);
    }

}
