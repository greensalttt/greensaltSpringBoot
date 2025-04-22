package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.cust.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegisterController {

    private final CustService custService;
    @GetMapping("/add")
    public String register() {
        return "registerForm";
    }

//    중복 이메일 체크
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
            String verificationCode = custService.joinEmail(email);
            System.out.println("컨트롤러에서 받아온 인증번호:  " + verificationCode);

            HttpSession session = request.getSession();
            session.setAttribute("verificationCode", verificationCode); // 세션에 인증번호 저장
            System.out.println("세션에 저장된 저장 인증번호: " + session.getAttribute("verificationCode"));

            return verificationCode;
        } catch (Exception e) {
            return "errorPage";
        }
    }


    //    중복 넥네임 체크
    @PostMapping("/nickCheck")
    public @ResponseBody String nickCheckPost(@RequestParam("cNick") String cNick) {
        try {
            String nickCheckResult = custService.nickCheck(cNick);
            return nickCheckResult;
        } catch (Exception e) {
            // 예외 처리 로직
            return "nickError";
        }
    }

    @PostMapping("/add")
    public String registerPost(Cust cust, HttpServletRequest request, @RequestParam("emailCode") String userInputCode) {
        if(!custService.save(cust, request, userInputCode)){
        return "errorPage";
        }
        return "loginForm";
    }
}
