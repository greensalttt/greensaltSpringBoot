package com.example.greenspringboot.cust.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

public class AdminController {

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }



}
