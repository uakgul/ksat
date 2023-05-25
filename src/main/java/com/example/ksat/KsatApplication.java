package com.example.ksat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories({"com.example.ksat.repository"})
@EnableJpaAuditing
@EnableTransactionManagement
@EnableScheduling
public class KsatApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsatApplication.class, args);
    }

}
