package com.example.lab71;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class Lab71Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab71Application.class, args);
    }

}
