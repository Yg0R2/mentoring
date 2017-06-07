package com.epam.training;

import com.epam.training.common.AbstractFactory;
import com.epam.training.person.Person;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        Application instance = new Application();

        try {
            instance.run(AbstractFactory.STORAGE_TYPE.DB);
        }
        catch (Exception e) {
            LOGGER.error("Error during use db storage.", e);
        }

        try {
            instance.run(AbstractFactory.STORAGE_TYPE.FILE);
        }
        catch (Exception e) {
            LOGGER.error("Error during use file storage.", e);
        }
    }

    private void run(AbstractFactory.STORAGE_TYPE storageType) throws Exception {
        LOGGER.info("--- " + storageType + " Storage ---");

        Person fred = new Person("Fred", 32);
        Person john = new Person("John", 23);

        AbstractFactory writerFactory = FactoryProducer.getFactory(FactoryProducer.OPERATION_TYPE.WRITER);

        Writer writer = writerFactory.getWriter(storageType);
        writer.writePerson(fred);
        writer.writePerson(john);

        AbstractFactory readerFactory = FactoryProducer.getFactory(FactoryProducer.OPERATION_TYPE.READER);

        Reader reader = readerFactory.getReader(storageType);

        List<Person> personList = reader.readPersons();
        LOGGER.info("Count of stored people: " + personList.size());

        LOGGER.info("Stored people:");
        for (Person person : personList) {
            LOGGER.info("  " + person.toString());
        }

        Person person = reader.readPerson("Albert");
        LOGGER.info("Albert exists in the storage: " + String.valueOf(person != null));

        person = reader.readPerson(john.getName());
        LOGGER.info(john.getName() + " exists in the storage: " + String.valueOf(person != null));
    }

}
