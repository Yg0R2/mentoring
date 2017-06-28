package com.epam.training.controller;

import com.epam.training.controller.helper.ControllerHelper;
import com.epam.training.payment.Payment;
import com.epam.training.payment.PaymentResponseErrorHandler;
import com.epam.training.exception.ResponseException;
import com.epam.training.payment.method.payfriend.PayFriendRequest;
import com.epam.training.payment.method.payfriend.PayFriendResponse;
import com.epam.training.payment.method.payfriend.PayFriend;
import com.epam.training.ConfigKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PayFriendPaymentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayFriendPaymentController.class);

    @GetMapping(value = ConfigKeys.PAYFRIEND_API_PAY_URL)
    public ModelAndView payForm() {
        PayFriendRequest payFriendRequest = getTestPayFriendRequest();

        LOGGER.debug("Populate form with default data:");
        LOGGER.debug(payFriendRequest.toString());

        Map<String, Object> map = new HashMap<>();

        map.put("request", payFriendRequest);
        map.put("payFriendPayURL", ConfigKeys.PAYFRIEND_API_PAY_URL);

        return new ModelAndView("payFriend/payFriendPayForm", map);
    }

    @PostMapping(value = ConfigKeys.PAYFRIEND_API_PAY_URL)
    public String paySubmit(@ModelAttribute("payFriendRequest") PayFriendRequest payFriendRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String bindingErrors = ControllerHelper.getBindingErrorsAsString(bindingResult);

            LOGGER.error(bindingErrors);

            return bindingErrors;
        }

        PayFriendResponse response;
        try {
            response = getTestPayFriendResponse(payFriendRequest);
        }
        catch (ResponseException e) {
            LOGGER.error(e.getMessage(), e);

            return e.getBody();
        }

        LOGGER.debug("Response:");
        LOGGER.debug(response.toString());

        return response.toFormattedString();
    }


    private PayFriend getTestPayFriend(String prefix) {
        PayFriend friend = new PayFriend();

        friend.setEmail(prefix + "Friend@email.com");
        friend.setName(prefix + "FriendName");
        friend.setUserId(prefix + "FriendId");

        return friend;
    }

    @ModelAttribute("payFriendRequest")
    private PayFriendRequest getTestPayFriendRequest() {
        PayFriendRequest request = new PayFriendRequest();

        request.setPayFriend(getTestPayFriend("Pay"));
        request.setPayment(getTestPayment());
        request.setTargetFriend(getTestPayFriend("Target"));

        return request;
    }

    private PayFriendResponse getTestPayFriendResponse(PayFriendRequest payFriendRequest) {
        RestTemplate template = new RestTemplate();
        template.setErrorHandler(new PaymentResponseErrorHandler());

        PayFriendResponse response = template.postForObject(
            ConfigKeys.PAYFRIEND_PAY_SERVICE_URL, payFriendRequest, PayFriendResponse.class);

        return response;
    }

    private Payment getTestPayment() {
        Payment payment = new Payment();

        payment.setAmount(100.2);
        payment.setCurrency("USD");

        return payment;
    }

}
