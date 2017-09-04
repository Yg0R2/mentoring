package com.epam.mentoring.chat.controller;

import com.epam.mentoring.chat.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller("/app")
public class ChatController {

    @MessageMapping("/chat.addUser")
    @SendTo("/channel/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        Map<String, Object> sessionAttributesMap = simpMessageHeaderAccessor.getSessionAttributes();

        sessionAttributesMap.put("userName", chatMessage.getSender());

        return chatMessage;
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/channel/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

}
