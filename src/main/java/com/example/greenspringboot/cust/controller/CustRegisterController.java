package com.example.greenspringboot.cust.controller;

//import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.cust.service.CustService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class CustRegisterController {

    @Autowired
    CustService custService;
    @GetMapping("/register")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/emailCCheck")
    public @ResponseBody String emailCheck(@RequestParam("cEmail") String cEmail) {
        try {
            String checkResult = custService.emailCheck(cEmail);
            return checkResult;
        } catch (Exception e) {
            // 예외 처리 로직
            return "Invalid email address";
        }
    }
}
