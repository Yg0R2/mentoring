package com.epam.training.payment;

import com.epam.training.exception.ResponseException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class PaymentResponseErrorHandler implements ResponseErrorHandler {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return errorHandler.hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        ResponseException exception = new ResponseException();

        exception.setStatusCode(response.getStatusCode());
        exception.setBody(IOUtils.toString(response.getBody()));
        exception.setHeaders(response.getHeaders());

        throw exception;
    }

}
