package com.epam.training.controller;

import com.epam.training.ConfigKeys;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloWorldController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView helloWorld() {
        Map<String, String> map = new HashMap<>();

        map.put("creditCardPayURL", ConfigKeys.CREDIT_CARD_API_PAY_URL);
        map.put("creditCardPayLabel", "Pay with Credit Card");

        map.put("payFriendPayURL", ConfigKeys.PAYFRIEND_API_PAY_URL);
        map.put("payFriendPayLabel", "Pay with PayFriend");

        return new ModelAndView("index", map);
    }
}
