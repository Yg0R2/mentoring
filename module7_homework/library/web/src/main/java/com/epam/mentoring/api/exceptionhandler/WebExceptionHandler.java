package com.epam.mentoring.api.exceptionhandler;

import com.epam.mentoring.service.exception.NoSuchEntryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class WebExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebExceptionHandler.class);

    @ExceptionHandler(NoSuchEntryException.class)
    public ResponseEntity<Object> handleNoSuchEntryException(NoSuchEntryException e, HttpServletRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(e, request);

        LOGGER.error(errorInfo.toString());

        return new ResponseEntity<>(errorInfo, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
