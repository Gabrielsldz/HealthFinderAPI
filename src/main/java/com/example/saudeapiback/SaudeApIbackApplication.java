package com.example.saudeapiback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableRetry
@SpringBootApplication
@EnableAsync
public class SaudeApIbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaudeApIbackApplication.class, args);
    }

}
