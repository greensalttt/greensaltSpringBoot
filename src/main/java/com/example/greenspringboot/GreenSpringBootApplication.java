package com.example.greenspringboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;

//오더 스케줄러
@EnableScheduling
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class GreenSpringBootApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(GreenSpringBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GreenSpringBootApplication.class);
    }


}
