package com.epam.mentoring.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DummyEmailService implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyEmailService.class);

    @Override
    public void sendEmail(String emailAddress, String content) {
        LOGGER.info("Sending email to: {}\nWith content:\n{}", emailAddress, content);
    }

}
