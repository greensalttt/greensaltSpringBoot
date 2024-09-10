package com.example.greenspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class GreenSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GreenSpringBootApplication.class, args);
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

}
