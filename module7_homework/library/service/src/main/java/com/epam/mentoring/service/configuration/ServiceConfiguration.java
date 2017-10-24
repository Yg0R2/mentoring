package com.epam.mentoring.service.configuration;

import com.epam.mentoring.service.email.DummyEmailService;
import com.epam.mentoring.service.email.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public EmailService emailService() {
        return new DummyEmailService();
    }

}
