package com.example.greenspringboot.cust.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustController {

    @GetMapping("/test")
    public String getHello(){
        return "test";
    }
    @PostMapping("/join")
    public String join() {
        return "String";
    }
}


