package com.epam.mentoring.web.converter;

import com.epam.mentoring.api.controller.BorrowRestController;
import com.epam.mentoring.request.BorrowRequest;
import com.epam.mentoring.response.BorrowResponse;
import com.epam.mentoring.mapper.BorrowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BorrowConverter implements Converter<String, BorrowRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowConverter.class);

    @Autowired
    private BorrowMapper borrowMapper;
    @Autowired
    private BorrowRestController borrowRestController;

    @Override
    public BorrowRequest convert(String source) {
        try {
            long longValue = Long.valueOf(source);

            BorrowResponse borrowResponse = borrowRestController.getBorrow(longValue);

            return borrowMapper.mapToRequest(borrowResponse);
        }
        catch (Exception e) {
            LOGGER.error("Unable to convert the given source: '{}' to borrow Id.", source);
            LOGGER.error(e.getMessage(), e);

            return null;
        }
    }

}
