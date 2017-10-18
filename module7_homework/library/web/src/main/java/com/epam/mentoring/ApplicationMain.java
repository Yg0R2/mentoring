package com.epam.mentoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationMain {

    @Autowired
    private DummyAuthorDataPopulator dummyAuthorDataPopulator;
    @Autowired
    private DummyBookDataPopulator dummyBookDataPopulator;
    @Autowired
    private DummyInventoryDataPopulator dummyInventoryDataPopulator;
    @Autowired
    private DummyUserDataPopulator dummyUserDataPopulator;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            dummyAuthorDataPopulator.populateAuthors();
            dummyBookDataPopulator.populateBooks();
            dummyUserDataPopulator.populateUsers();
            dummyInventoryDataPopulator.populateInventories();
        };
    }

}
