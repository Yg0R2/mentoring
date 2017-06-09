package com.epam.training;

import com.epam.training.person.Person;
import com.epam.training.reader.impl.DBReader;
import com.epam.training.reader.impl.ProxyReader;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.impl.DBWriter;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        Person fred = new Person("Fred", 32);

        Writer writer = new DBWriter();
        writer.writePerson(fred);

        Reader reader = new DBReader();
        Reader proxyReader = new ProxyReader(reader);

        Person person = proxyReader.readPerson(fred.getName());
        LOGGER.info(person.toString());

        person = proxyReader.readPerson(fred.getName());
        LOGGER.info(person.toString());
    }

}
