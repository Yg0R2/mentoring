package com.epam.mentoring.chat.store;

import com.epam.mentoring.chat.message.ChatMessage;

import java.io.IOException;

public interface ChatStore {

    void storeMessage(ChatMessage chatMessage) throws IOException;

}
