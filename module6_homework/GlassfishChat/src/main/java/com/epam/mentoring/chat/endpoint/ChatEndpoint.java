package com.epam.mentoring.chat.endpoint;

import com.epam.mentoring.chat.message.ChatMessage;
import com.epam.mentoring.chat.message.ChatMessageDecoder;
import com.epam.mentoring.chat.message.ChatMessageEncoder;
import com.epam.mentoring.chat.store.ChatStore;
import com.epam.mentoring.chat.store.CvsChatStore;
import com.epam.mentoring.chat.store.JsonChatStore;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/ws", encoders = {ChatMessageEncoder.class}, decoders = {ChatMessageDecoder.class})
public class ChatEndpoint {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass().getName());

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    private ChatStore cvsChatStore;
    private ChatStore jsonChatStore;
    private Gson gson = new Gson();

    public ChatEndpoint() throws IOException {
        cvsChatStore = new CvsChatStore();
        jsonChatStore = new JsonChatStore();
    }

    @OnOpen
    public void onOpen(Session peer) {
        LOGGER.debug("Session opened.");

        peers.add(peer);
    }

    @OnClose
    public void onClose(Session peer) {
        LOGGER.debug("Session closed.");

        peers.remove(peer);
    }

    @OnMessage
    public void onMessage(ChatMessage chatMessage) {
        try {
            broadcast(chatMessage);
        }
        catch (IOException | EncodeException e) {
            LOGGER.error("Unable to broadcast the message: " + gson.toJson(chatMessage), e);
        }

        try {
            cvsChatStore.storeMessage(chatMessage);
            jsonChatStore.storeMessage(chatMessage);
        }
        catch (Exception  e) {
            LOGGER.error("Unable to store the message: " + gson.toJson(chatMessage), e);
        }

        LOGGER.debug("On message.");
    }

    private void broadcast(ChatMessage chatMessage) throws IOException, EncodeException {
        for (Session peer : peers) {
            peer.getBasicRemote().sendObject(chatMessage);
        }
    }

}