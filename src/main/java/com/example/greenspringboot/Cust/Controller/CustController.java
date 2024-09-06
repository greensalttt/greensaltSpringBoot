package com.example.greenspringboot.Cust.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustController {

    @GetMapping("/hello")
    public String getHello(){
        return "HelloSpringBoot";
    }
}
