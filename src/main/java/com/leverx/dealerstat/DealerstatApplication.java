package com.leverx.dealerstat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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
