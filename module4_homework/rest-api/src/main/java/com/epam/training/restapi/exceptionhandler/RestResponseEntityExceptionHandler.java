package com.epam.training.restapi.exceptionhandler;

import com.epam.training.restapi.exception.BadUserRequestException;
import com.epam.training.restapi.exception.DuplicateUserException;
import com.epam.training.restapi.exception.UserNotFoundException;
import com.epam.training.restapi.utils.ResponseUtils;
import com.epam.training.restapi.utils.StringUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Gson gson;

    @ExceptionHandler(value = BadUserRequestException.class)
    public ResponseEntity<Object> handleBadUserRequestException(RuntimeException e) {
        return ResponseUtils.getResponseEntity(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity<Object> handleDuplicateUserException(RuntimeException e, WebRequest request) {
        String errorMessage = e.getMessage();
        if (StringUtils.isEmpty(errorMessage)) {
            Object uriParameters = request.getParameterMap();

            errorMessage = "Unable to create user. User exists with: " + gson.toJson(uriParameters);
        }

        return ResponseUtils.getResponseEntity(HttpStatus.CONFLICT, errorMessage);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
        HttpRequestMethodNotSupportedException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return ResponseUtils.getResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(RuntimeException e, WebRequest request) {
        String errorMessage = e.getMessage();
        if (StringUtils.isEmpty(errorMessage)) {
            Object uriVariables = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, 0);

            errorMessage = "Unable to fetch user with: " + gson.toJson(uriVariables);
        }

        return ResponseUtils.getResponseEntity(HttpStatus.NOT_FOUND, errorMessage);
    }

}
