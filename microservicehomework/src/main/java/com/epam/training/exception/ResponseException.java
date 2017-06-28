package com.epam.training.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ResponseException extends RuntimeException {

    private String body;
    private HttpHeaders headers;
    private HttpStatus statusCode;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(HttpHeaders headers) {
        this.headers = headers;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

}
