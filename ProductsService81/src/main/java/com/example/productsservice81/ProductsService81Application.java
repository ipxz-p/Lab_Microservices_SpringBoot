package com.example.productsservice81;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class ProductsService81Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductsService81Application.class, args);
    }

}
