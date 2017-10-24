package com.epam.mentoring;

import com.epam.mentoring.service.email.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApplicationMain {

    @Autowired
    private DummyAuthorDataPopulator dummyAuthorDataPopulator;
    @Autowired
    private DummyBookDataPopulator dummyBookDataPopulator;
    @Autowired
    private DummyBorrowDataPopulator dummyBorrowDataPopulator;
    @Autowired
    private DummyInventoryDataPopulator dummyInventoryDataPopulator;
    @Autowired
    private DummyUserDataPopulator dummyUserDataPopulator;
    @Autowired
    private ReminderService reminderService;

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
            dummyBorrowDataPopulator.populateBorrows();
            reminderService.remindUsers();
        };
    }

}
