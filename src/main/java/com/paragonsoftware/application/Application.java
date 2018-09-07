package com.paragonsoftware.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author IvIsmakaev
 */
@SpringBootApplication
@EntityScan("com.paragonsoftware.db")
@EnableJpaRepositories(basePackages = "com.paragonsoftware.db.repo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}