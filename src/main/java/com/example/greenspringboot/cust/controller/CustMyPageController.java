package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class CustMyPageController {

    private final CustRepository custRepository;


    @GetMapping("/list")
    public String myPage(HttpSession session){
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPage";
    }

//    엔티티는 DB 전송, DTO는 데이터 전송
    @GetMapping("/info")
    public String myPageInfo(HttpSession session){
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPageInfo";
    }
}
