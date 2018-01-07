package com.home.dreamcar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.home.dreamcar.repository")
public class DreamCarApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamCarApplication.class, args);
    }
}
