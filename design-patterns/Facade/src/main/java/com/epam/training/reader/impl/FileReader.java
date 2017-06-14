package com.epam.training.reader.impl;

import com.epam.training.common.FileHelper;
import com.epam.training.person.Person;
import com.epam.training.reader.model.Reader;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

public class FileReader extends FileHelper implements Reader {

    public FileReader(File outputFile) throws IOException {
        super(outputFile);
    }

    @Override
    public Person readPerson(String name) throws IOException {
        List<Person> personList = readPersons();

        Optional<Person> person = personList.stream().filter(p -> name.equals(p.getName())).findFirst();

        if (person.isPresent()) {
            return person.get();
        }

        return null;
    }

    @Override
    public List<Person> readPersons() throws IOException {
        JsonArray jsonArray = readFile();

        Type type = new TypeToken<List<Person>>() {}.getType();

        return gson.fromJson(jsonArray, type);
    }

}
