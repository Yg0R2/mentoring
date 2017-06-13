package com.epam.training;

import com.epam.training.person.Person;
import com.epam.training.reader.decorator.PersonInputStream;
import com.epam.training.reader.impl.FileReader;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.decorator.PersonOutputStream;
import com.epam.training.writer.impl.FileWriter;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        Person joe = new Person("joe", 55);

        Writer writer = new PersonOutputStream(new FileWriter());
        writer.writePerson(joe);

        Reader reader = new PersonInputStream(new FileReader());
        Person person = reader.readPerson(joe.getName());

        LOGGER.info("Original person: " + joe.toString());
        LOGGER.info("Decorated person: " + person.toString());
    }

}
