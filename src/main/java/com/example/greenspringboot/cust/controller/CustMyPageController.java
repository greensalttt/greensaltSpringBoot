package com.example.greenspringboot.cust.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustMyPageController {

    @GetMapping("/myPage")
    public String myPage(){
        return "myPage";
    }
}
