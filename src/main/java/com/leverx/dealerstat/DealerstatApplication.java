package com.leverx.dealerstat;

import com.leverx.dealerstat.serviceimpl.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DealerstatApplication {

    @Autowired
    private SendEmailService sendEmailService;


    public static void main(String[] args) {
        SpringApplication.run(DealerstatApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmailFromStart() {
//        sendEmailService.sendEmail("Aleshkis@mail.ru", "Hi", "FinalProjectt");
//    }

}
