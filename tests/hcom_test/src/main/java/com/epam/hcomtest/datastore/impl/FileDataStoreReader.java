package com.epam.hcomtest.datastore.impl;

import com.epam.hcomtest.datastore.exception.InvalidDataStoreFormatException;
import com.epam.hcomtest.datastore.exception.DataStoreNotFoundException;
import com.epam.hcomtest.datastore.model.DataStoreReader;
import com.epam.hcomtest.resource.Resource;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class FileDataStoreReader implements DataStoreReader {

    @Override
    public List<Resource> readResources(String resourceName) {
        List<Resource> resources = new ArrayList<>();

        try (Stream<String> stream = Files.lines(getFilePath(resourceName))) {
            stream.forEach(line -> resources.add(getNewResourceFromLine(line)));
        }
        catch (IOException e) {
            throw new DataStoreNotFoundException("Requested resource '" + resourceName + "' not found.");
        }

        return resources;
    }

    private Resource getNewResourceFromLine(String line) {
        String regexPattern = "^(\\d+),([a-zA-Z])$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);

        if (!matcher.find()) {
            throw new InvalidDataStoreFormatException(
                "Given '" + line + "' format is wrong. It should match: '" + regexPattern +"'");
        }

        int key = Integer.valueOf(matcher.group(1));
        String value = matcher.group(2);

        return new Resource(key, value);
    }

    private Path getFilePath(String fileName) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(fileName);
        File file = classPathResource.getFile();

        return Paths.get(file.getPath());
    }

}
