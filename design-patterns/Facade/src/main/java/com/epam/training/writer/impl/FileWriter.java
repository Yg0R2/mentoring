package com.epam.training.writer.impl;

import com.epam.training.common.FileHelper;
import com.epam.training.person.Person;
import com.epam.training.writer.model.Writer;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class FileWriter extends FileHelper implements Writer{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriter.class);

    public FileWriter(File outputFile) throws IOException {
        super(outputFile);
    }

    @Override
    public void updatePerson(Person person) throws Exception {
        JsonArray jsonArray = readFile();

        for (JsonElement element : jsonArray) {
            Person readPerson = gson.fromJson(element, Person.class);

            if (readPerson.compareTo(person) == 0) {
                element.getAsJsonObject().addProperty("iq", person.getIq());

                break;
            }
        }

        writeJsonArray(jsonArray);

    }

    @Override
    public void writePerson(Person person) throws IOException {
        JsonArray jsonArray = readFile();
        jsonArray.add(personToJsonElement(person));

        writeJsonArray(jsonArray);

        LOGGER.debug("Person successfully saved: " + person);
    }

    private void writeJsonArray(JsonArray jsonArray) throws IOException {
        try (java.io.FileWriter fileWriter = new java.io.FileWriter(outputFile)) {
            fileWriter.write(jsonArray.toString());
        }
        catch (IOException e) {
            LOGGER.error("Failed to write Person to file.", e);

            throw e;
        }
    }

}
