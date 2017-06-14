package com.epam.training.common;

import com.epam.training.person.Person;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

    protected File outputFile;
    protected Gson gson = new Gson();

    public FileHelper(File outputFile) throws IOException {
        this.outputFile = outputFile;

        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        }
    }

    protected JsonElement personToJsonElement(Person person) {
        String personJsonString = gson.toJson(person);

        return gson.fromJson(personJsonString, JsonElement.class);
    }

    protected JsonArray readFile() throws IOException {
        try (FileReader fileReader = new FileReader(outputFile)) {
            JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);

            if (jsonArray == null) {
                jsonArray = new JsonArray();
            }

            return jsonArray;
        }
    }

}
