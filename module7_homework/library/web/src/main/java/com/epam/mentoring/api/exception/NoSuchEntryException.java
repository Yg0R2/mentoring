package com.epam.mentoring.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NoSuchEntryException extends RuntimeException {

    public NoSuchEntryException(String message) {
        super(message);
    }

}
