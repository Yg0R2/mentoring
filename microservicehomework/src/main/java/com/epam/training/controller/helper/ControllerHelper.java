package com.epam.training.controller.helper;

import com.epam.training.payment.PaymentResponseErrorHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.client.RestTemplate;

public final class ControllerHelper {

    public static String getBindingErrorsAsString(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder(bindingResult.getErrorCount());

        for (ObjectError error : bindingResult.getAllErrors()) {
            sb.append(error.toString());
        }

        return sb.toString();
    }

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new PaymentResponseErrorHandler());

        return restTemplate;
    }

}
