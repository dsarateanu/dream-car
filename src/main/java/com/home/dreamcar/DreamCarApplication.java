package com.home.dreamcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaRepositories("com.home.dreamcar.repository")
@EnableScheduling
public class DreamCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamCarApplication.class, args);
    }
}
