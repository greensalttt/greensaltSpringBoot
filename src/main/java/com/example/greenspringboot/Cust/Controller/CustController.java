package com.example.greenspringboot.Cust.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustController {

    @GetMapping("/test")
    public String getHello(){
        return "test";
    }
}
