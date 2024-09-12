package com.example.greenspringboot.cust.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController
@Controller
@RequiredArgsConstructor
public class CustLoginController {
    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }
}


