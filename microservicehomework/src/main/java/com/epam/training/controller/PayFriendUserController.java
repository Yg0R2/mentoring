package com.epam.training.controller;

import com.epam.training.ConfigKeys;
import com.epam.training.controller.helper.ControllerHelper;
import com.epam.training.exception.ResponseException;
import com.epam.training.user.UserRequest;
import com.epam.training.user.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PayFriendUserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayFriendUserController.class);

    @GetMapping(value = ConfigKeys.PAYFRIEND_API_GET_USER_URL)
    public ModelAndView getUserForm() {
        UserRequest userRequest = getTestUserRequest();

        LOGGER.debug("Populate 'getUser' form with default data:");
        LOGGER.debug(userRequest.toString());

        Map<String, Object> map = new HashMap<>();

        map.put("request", userRequest);
        map.put("getUserURL", ConfigKeys.PAYFRIEND_API_GET_USER_URL);

        return new ModelAndView("payFriend/payFriendGetUserForm", map);
    }

    @PostMapping(value = ConfigKeys.PAYFRIEND_API_GET_USER_URL)
    public String getUser(@ModelAttribute("userRequest") UserRequest userRequest) {
        UserResponse response;
        try {
            response = getTestUserResponse(userRequest);
        }
        catch (ResponseException e) {
            LOGGER.error(e.getMessage(), e);

            return e.getBody();
        }

        LOGGER.debug("Response:");
        LOGGER.debug(response.toString());

        return response.toFormattedString();
    }

    @ModelAttribute("userRequest")
    private UserRequest getTestUserRequest() {
        UserRequest request = new UserRequest();

        request.setEmail("user@email.com");

        return request;
    }

    private UserResponse getTestUserResponse(UserRequest userRequest) {
        RestTemplate restTemplate = ControllerHelper.getRestTemplate();

        UserResponse response = restTemplate.postForObject(
            ConfigKeys.PAYFRIEND_GET_USER_URL, userRequest, UserResponse.class);

        return response;
    }

}
