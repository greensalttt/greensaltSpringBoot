package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.service.CustService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ForgotPwdController {


    @Autowired
    private CustService custService;

    @GetMapping("/forgotPwd")
    public String forgotPwd() {
        return "forgotPwd";
    }

    @PostMapping("/emailCheck")
    public @ResponseBody String emailCheckPost(@RequestParam("cEmail") String cEmail) {
        try {
            String checkResult = custService.emailCheck(cEmail);
            return checkResult;
        } catch (Exception e) {
            // 예외 처리 로직
            return "Invalid email address";
        }
    }

    @GetMapping("/verifyEmail")
    @ResponseBody
    public String verifyEmail(String email, HttpServletRequest request) {
        try{
            System.out.println("이메일 인증 이메일 : " + email);
            // 서비스로 인증번호 받아오기
            String verificationCode = custService.joinEmail2(email);
            System.out.println("컨트롤러에서 받아온 인증번호:  " + verificationCode);

            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode); // 세션에 인증번호 저장
            System.out.println("세션에 저장된 저장 인증번호: " + session.getAttribute("verificationCode"));

            return verificationCode;
        } catch (Exception e) {
            return "errorPage";
        }
    }


    @PostMapping("/forgotPwd")
    public String forgotPwd2(String cEmail, HttpServletRequest request) {
        custService.forgotPwdCId(cEmail, request);
        return "forgotPwd2";
    }
}