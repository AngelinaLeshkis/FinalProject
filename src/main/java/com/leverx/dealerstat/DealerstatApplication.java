package com.leverx.dealerstat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DealerstatApplication {

    public static void main(String[] args) {
        SpringApplication.run(DealerstatApplication.class, args);
    }

}
