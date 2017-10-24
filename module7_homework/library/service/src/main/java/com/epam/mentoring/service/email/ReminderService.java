package com.epam.mentoring.service.email;

import com.epam.mentoring.domain.BorrowDAO;
import com.epam.mentoring.service.BorrowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ReminderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReminderService.class);

    @Autowired
    private BorrowService borrowService;
    @Autowired
    private EmailService emailService;

    @PostConstruct
    @Scheduled(cron = "0 0 7 * * MON-FRI")
    public void remindUsers() {
        List<BorrowDAO> expiredBorrows = borrowService.getNotReturnedAndExpiredBorrows();

        LOGGER.info("Reminding {} user(s) whom have expired books.", expiredBorrows.size());

        expiredBorrows.stream().forEach(expiredBorrow -> {
            emailService.sendEmail(expiredBorrow.getUserBorrowed().getEmailAddress(), createContent(expiredBorrow));
        });
    }

    private String createContent(BorrowDAO borrow) {
        StringBuilder sb = new StringBuilder();

        sb.append("You have an expired book: ");
        sb.append(borrow.getBook().getTitle());
        sb.append(".\nExpiration date was: ");
        sb.append(borrow.getReturnDate());
        sb.append(".\nPlease return as soon as possible.");

        return sb.toString();
    }

}
