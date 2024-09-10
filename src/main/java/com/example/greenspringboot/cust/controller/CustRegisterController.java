package com.example.greenspringboot.cust.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
@RequiredArgsConstructor
public class CustRegisterController {
    @GetMapping("/register")
    public String register() {
        return "registerForm";
    }
}
