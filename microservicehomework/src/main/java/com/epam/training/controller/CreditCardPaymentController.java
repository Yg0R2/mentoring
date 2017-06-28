package com.epam.training.controller;

import com.epam.training.controller.helper.ControllerHelper;
import com.epam.training.payment.Payment;
import com.epam.training.exception.ResponseException;
import com.epam.training.payment.method.creditcard.*;
import com.epam.training.ConfigKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static com.epam.training.controller.helper.ControllerHelper.getBindingErrorsAsString;

@RestController
public class CreditCardPaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardPaymentController.class);

    @GetMapping(value = ConfigKeys.CREDIT_CARD_API_PAY_URL)
    public ModelAndView payForm() {
        PaymentRequest paymentRequest = getTestPaymentRequest();

        LOGGER.debug("Populate credit card payment form with default data:");
        LOGGER.debug(paymentRequest.toString());

        Map<String, Object> map = new HashMap<>();

        map.put("request", paymentRequest);
        map.put("creditCardPayURL", ConfigKeys.CREDIT_CARD_API_PAY_URL);

        return new ModelAndView("creditCard/creditCardPayForm", map);
    }

    @PostMapping(value = ConfigKeys.CREDIT_CARD_API_PAY_URL)
    public String paySubmit(@ModelAttribute("paymentRequest") PaymentRequest paymentRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String bindingErrors = ControllerHelper.getBindingErrorsAsString(bindingResult);

            LOGGER.error(bindingErrors);

            return bindingErrors;
        }

        PaymentResponse response;
        try {
            response = getTestPaymentResponse(paymentRequest);
        }
        catch (ResponseException e) {
            LOGGER.error(e.getMessage(), e);

            return e.getBody();
        }

        LOGGER.debug("Response:");
        LOGGER.debug(response.toString());

        return response.toFormattedString();
    }

    private CreditCard getTestCreditCard() {
        ExpirationDate expirationDate = new ExpirationDate();
        expirationDate.setMonth(11);
        expirationDate.setYear(21);

        CreditCard creditCard = new CreditCard();

        creditCard.setCreditCardNumber("4111111111111111");
        creditCard.setCvc(123);
        creditCard.setExpirationDate(expirationDate);

        return creditCard;
    }

    private Customer getTestCustomer() {
        Customer customer = new Customer();

        customer.setAddress("CustomerAddress");
        customer.setDateOfBirth(new Integer[] {1980, 05, 15});
        customer.setEmailAddress("Customer@email.com");
        customer.setFirstName("CustomerFirstName");
        customer.setLastName("CustomerLastName");

        return customer;
    }

    private Payment getTestPayment() {
        Payment payment = new Payment();

        payment.setAmount(100.2);
        payment.setCurrency("USD");

        return payment;
    }

    @ModelAttribute("paymentRequest")
    private PaymentRequest getTestPaymentRequest() {
        PaymentRequest request = new PaymentRequest();

        request.setCreditCard(getTestCreditCard());
        request.setCustomer(getTestCustomer());
        request.setPayment(getTestPayment());

        return request;
    }

    private PaymentResponse getTestPaymentResponse(PaymentRequest paymentRequest) {
        RestTemplate template = ControllerHelper.getRestTemplate();

        PaymentResponse response = template.postForObject(
            ConfigKeys.CREDIT_CARD_PAY_SERVICE_URL, paymentRequest, PaymentResponse.class);

        return response;
    }

}
