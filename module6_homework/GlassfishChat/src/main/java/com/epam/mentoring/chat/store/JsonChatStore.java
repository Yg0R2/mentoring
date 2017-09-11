package com.epam.mentoring.chat.store;

import com.epam.mentoring.chat.message.ChatMessage;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class JsonChatStore implements ChatStore {

    private Gson gson = new Gson();
    private File jsonFile;

    public JsonChatStore() throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");

        jsonFile = new File(tmpDir + "/chatMessages-" + LocalDate.now() + ".json");
    }

    @Override
    public void storeMessage(ChatMessage chatMessage) throws IOException {
        String line = gson.toJson(chatMessage);

        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }

        Files.write(jsonFile.toPath(), Arrays.asList(line), StandardOpenOption.APPEND);
    }

}
