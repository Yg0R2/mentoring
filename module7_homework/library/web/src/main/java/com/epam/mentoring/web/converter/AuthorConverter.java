package com.epam.mentoring.web.converter;

import com.epam.mentoring.api.controller.AuthorRestController;
import com.epam.mentoring.api.request.AuthorRequest;
import com.epam.mentoring.api.response.AuthorResponse;
import com.epam.mentoring.mapper.AuthorMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
public class AuthorConverter implements Converter<String, AuthorRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorConverter.class);

    @Autowired
    private AuthorMapper authorMapper;
    @Autowired
    private AuthorRestController authorRestController;

    @Override
    public AuthorRequest convert(String source) {
        try {
            long longValue = Long.valueOf(source);

            AuthorResponse authorResponse = authorRestController.getAuthor(longValue);

            return authorMapper.mapToRequest(authorResponse);
        }
        catch (Exception e) {
            LOGGER.error("Unable to convert the given source: '{}' to author Id.", source);
            LOGGER.error(e.getMessage(), e);

            return null;
        }
    }

}
