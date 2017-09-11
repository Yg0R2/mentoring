package com.epam.mentoring.chat.store;

import com.epam.mentoring.chat.message.ChatMessage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class CvsChatStore implements ChatStore {

    private File cvsFile;

    public CvsChatStore() throws IOException {
        String tmpDir = System.getProperty("java.io.tmpdir");

        cvsFile = new File(tmpDir + "/chatMessages-" + LocalDate.now() + ".cvs");
    }

    @Override
    public void storeMessage(ChatMessage chatMessage) throws IOException {
        String line = chatMessage.toString();

        if (!cvsFile.exists()) {
            cvsFile.createNewFile();
        }

        Files.write(cvsFile.toPath(), Arrays.asList(line), StandardOpenOption.APPEND);
    }

}
