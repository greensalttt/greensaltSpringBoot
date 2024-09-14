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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping("/mailCheck")
    @ResponseBody
    public String mailCheck(String email, HttpServletRequest request) {
        try{
            System.out.println("이메일 인증 요청이 들어옴!");
            System.out.println("이메일 인증 이메일 : " + email + "이메일:");

            String verificationCode = custService.joinEmail(email); // 이메일로 인증번호 발송
            System.out.println("자바로 받아온 인증번호:  " + verificationCode);

            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode); // 세션에 인증번호 저장
            System.out.println("검증용 저장 인증번호: " + session.getAttribute("verificationCode"));

            return verificationCode;
        } catch (Exception e) {
            return "errorPage";
        }
    }
}
