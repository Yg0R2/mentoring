package com.epam.mentoring.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class MissingRequestParameterException extends RuntimeException {

    public MissingRequestParameterException(String message) {
        super(message);
    }

}
