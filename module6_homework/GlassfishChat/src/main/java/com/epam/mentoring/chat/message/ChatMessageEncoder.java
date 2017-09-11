package com.epam.mentoring.chat.message;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageEncoder.class);

    private Gson gson = new Gson();

    @Override
    public void init(final EndpointConfig config) {
        LOGGER.debug("Initializing encoder.");
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroying encoder.");
    }

    @Override
    public String encode(final ChatMessage chatMessage) throws EncodeException {
        return gson.toJson(chatMessage);
    }

}