package com.cyy.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class Springboot01AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01AsyncApplication.class, args);
    }

}
