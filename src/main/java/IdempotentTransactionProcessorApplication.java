package com.interview.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IdempotentTransactionProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                IdempotentTransactionProcessorApplication.class,
                args);
    }

}
