package com.epam.training.common;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileHelper {

    protected File outputFile;
    protected Gson gson = new Gson();

    public FileHelper() throws IOException {
        outputFile = new File("./target/output.json");

        if (!outputFile.exists()) {
            outputFile.getParentFile().mkdirs();
            outputFile.createNewFile();
        }
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
