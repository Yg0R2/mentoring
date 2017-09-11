package com.epam.mentoring.chat.message;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatMessageDecoder.class);

    private Gson gson = new Gson();

    @Override
    public void init(final EndpointConfig config) {
        LOGGER.debug("Initializing decoder.");
    }

    @Override
    public void destroy() {
        LOGGER.debug("Destroying decoder.");
    }

    @Override
    public ChatMessage decode(final String jsonMessage) throws DecodeException {
        return gson.fromJson(jsonMessage, ChatMessage.class);
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }

}
