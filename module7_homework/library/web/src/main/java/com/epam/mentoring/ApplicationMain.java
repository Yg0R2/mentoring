package com.epam.mentoring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApplicationMain {

    @Autowired
    private DummyDataPopulator dataPopulator;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            dataPopulator.populateBooksAndAuthors();
            dataPopulator.populateUsers();
        };
    }

}
