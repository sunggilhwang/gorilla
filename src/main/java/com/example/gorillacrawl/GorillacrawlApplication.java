package com.example.gorillacrawl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class GorillacrawlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GorillacrawlApplication.class, args);
    }

}
