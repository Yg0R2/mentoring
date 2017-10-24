package com.epam.mentoring.web.converter;

import com.epam.mentoring.api.controller.BookRestController;
import com.epam.mentoring.api.request.BookRequest;
import com.epam.mentoring.api.response.BookResponse;
import com.epam.mentoring.mapper.BookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements Converter<String, BookRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookConverter.class);

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRestController bookRestController;

    @Override
    public BookRequest convert(String source) {
        try {
            long longValue = Long.valueOf(source);

            BookResponse bookResponse = bookRestController.getBook(longValue);

            return bookMapper.mapToRequest(bookResponse);
        }
        catch (Exception e) {
            LOGGER.error("Unable to convert the given source: '{}' to book Id.", source);
            LOGGER.error(e.getMessage(), e);

            return null;
        }
    }

}
