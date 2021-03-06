package com.epam.training.restapi;

import com.epam.training.restapi.model.User;
import com.epam.training.restapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestApiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            LOGGER.info("Populate some default users...");

            repository.save(new User("Jack", "Bauer", "Jack_Bauer@epam.com"));
            repository.save(new User("Chloe", "O'Brian", "Chloe_O'Brian@epam.com"));
            repository.save(new User("Kim", "Bauer", "Kim_Bauer@epam.com"));
            repository.save(new User("David", "Palmer", "David_Palmer@epam.com"));
            repository.save(new User("Michelle", "Dessler", "Michelle_Dessler@epam.com"));
        };
    }

}
