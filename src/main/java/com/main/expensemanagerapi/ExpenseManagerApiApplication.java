package com.main.expensemanagerapi;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
@EnableMongock
public class ExpenseManagerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseManagerApiApplication.class, args);
    }

}
