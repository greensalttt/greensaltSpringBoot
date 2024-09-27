package com.example.greenspringboot.cust.controller;

import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CustMyPageController {

    @GetMapping("/myPage")
    public String myPage(HttpSession session){
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPage";
    }
}
