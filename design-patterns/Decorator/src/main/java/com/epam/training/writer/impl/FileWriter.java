package com.epam.training.writer.impl;

import com.epam.training.common.FileHelper;
import com.epam.training.person.Person;
import com.epam.training.writer.model.Writer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FileWriter extends FileHelper implements Writer{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriter.class);

    public FileWriter() throws IOException {
        super();
    }

    @Override
    public void writePerson(Person person) throws IOException {
        String personJsonString = gson.toJson(person);

        JsonElement personJsonElement = gson.fromJson(personJsonString, JsonElement.class);

        JsonArray jsonArray = readFile();
        jsonArray.add(personJsonElement);

        try (java.io.FileWriter fileWriter = new java.io.FileWriter(outputFile)) {
            fileWriter.write(jsonArray.toString());

            LOGGER.debug("Person successfully saved: " + person);
        }
        catch (IOException e) {
            LOGGER.error("Failed to write Person to file.", e);

            throw e;
        }
    }

}
