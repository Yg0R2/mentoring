package com.epam.training.reader.impl;

import com.epam.training.person.Person;
import com.epam.training.reader.model.Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProxyReader implements Reader {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyReader.class);

    private Reader realReader;
    private Map<String, Person> cache = new HashMap<>();

    public ProxyReader(Reader reader) {
        realReader = reader;
    }

    @Override
    public Person readPerson(String name) throws Exception {
        if (cache.containsKey(name)) {
            LOGGER.info("Person found in the cache.");

            return cache.get(name);
        }

        Person person = realReader.readPerson(name);

        cache.put(name, person);

        LOGGER.info("Person found in the Storage and added to the cache.");

        return person;
    }

    @Override
    public List<Person> readPersons() throws Exception {
        return realReader.readPersons();
    }

}
