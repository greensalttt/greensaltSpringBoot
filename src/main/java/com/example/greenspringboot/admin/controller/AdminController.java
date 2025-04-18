package com.example.greenspringboot.admin.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public class AdminController {

    @PostMapping("/adminLogin")
    public String adminLogin(){
        return "/";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }



}
