package com.epam.training;

import com.epam.training.person.Person;
import com.epam.training.person.PersonFacade;
import com.epam.training.reader.impl.FileReader;
import com.epam.training.reader.model.Reader;
import com.epam.training.writer.impl.FileWriter;
import com.epam.training.writer.model.Writer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
    private static final Random RND = new Random();

    public static void main(String[] args) throws Exception {
        Application instance = new Application(new File("./target/output.json"));

        instance.run();
    }

    private PersonFacade personFacade;
    private Reader reader;
    private Writer writer;

    public Application(File outputFile) throws Exception {
        outputFile.delete();

        reader = new FileReader(outputFile);

        writer = new FileWriter(outputFile);

        personFacade = new PersonFacade(writer);
    }

    public void run() throws Exception {
        populatePersonList();

        displayPersonListFromFile();

        taskSmarterPerson();

        taskMoveIq();

        taskIncreaseIq();
    }

    protected void taskIncreaseIq() throws Exception {
        List<Person> personList = reader.readPersons();

        LOGGER.info("------------ Increase IQ ------------");

        Person person = personList.get(4);

        LOGGER.info("Increase iq with " + PersonFacade.AMOUNT_OF_INCREASE_IQ + " of " + person.toString());

        personFacade.updatePersonIq(person);

        displayPersonListFromFile();
    }

    protected void taskMoveIq() throws Exception {
        List<Person> personList = reader.readPersons();

        LOGGER.info("-------------- Move IQ --------------");

        Person person1 = personList.get(2);
        Person person2 = personList.get(3);

        LOGGER.info("Move " + PersonFacade.AMOUNT_OF_MOVE_IQ + " iq points from " + person1.toString() + " to " + person2.toString());

        personFacade.moveIQ(person1, person2);

        displayPersonListFromFile();
    }

    protected void taskSmarterPerson() throws Exception {
        List<Person> personList = reader.readPersons();

        LOGGER.info("-------------- Smarter --------------");

        Person person1 = personList.get(0);
        Person person2 = personList.get(1);

        Person smarterPerson = personFacade.smarter(person1, person2);

        LOGGER.info("Which person is smarter: [" + person1.toString() + " or " + person2.toString() + "]");
        LOGGER.info(smarterPerson.toString() + " is smarter.");
    }

    private void displayPersonListFromFile() throws Exception {
        LOGGER.info("List all person:");

        List<Person> personList = reader.readPersons();
        personList.stream().forEach(p -> LOGGER.info(p.toString()));
    }

    private void populatePersonList() throws Exception {
        List<Person> personList = new ArrayList<>(5);

        for (int i = 0; i < 5; i++) {
            String name = "person-" + i;
            int age = RND.nextInt(50) + 20;
            int iq = RND.nextInt(180) + 60;

            personList.add(new Person(name, age, iq));
        }

        for (Person person : personList) {
            writer.writePerson(person);
        }
    }

}
