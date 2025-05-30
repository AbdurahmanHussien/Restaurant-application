package com.spring.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringRestaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestaurantApplication.class, args);
    }



}
