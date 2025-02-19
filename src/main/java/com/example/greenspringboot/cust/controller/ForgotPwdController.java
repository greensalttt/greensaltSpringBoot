package com.example.greenspringboot.cust.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ForgotPwdController {


    @GetMapping("/forgotPwd")
    public String forgotPwd() {
        return "forgotPwd";
    }
}
