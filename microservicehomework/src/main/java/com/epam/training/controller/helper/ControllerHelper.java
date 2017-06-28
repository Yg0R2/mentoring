package com.epam.training.controller.helper;

import com.epam.training.payment.PaymentResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

public final class ControllerHelper {

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new PaymentResponseErrorHandler());

        return restTemplate;
    }

}
