package com.epam.mentoring.web.converter;

import com.epam.mentoring.api.controller.UserRestController;
import com.epam.mentoring.api.request.UserRequest;
import com.epam.mentoring.api.response.UserResponse;
import com.epam.mentoring.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<String, UserRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConverter.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRestController userRestController;

    @Override
    public UserRequest convert(String source) {
        try {
            long longValue = Long.valueOf(source);

            UserResponse userResponse = userRestController.getUser(longValue);

            return userMapper.mapToRequest(userResponse);
        }
        catch (Exception e) {
            LOGGER.error("Unable to convert the given source: '{}' to user Id.", source);
            LOGGER.error(e.getMessage(), e);

            return null;
        }
    }

}
