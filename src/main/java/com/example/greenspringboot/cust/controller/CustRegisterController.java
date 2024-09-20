package com.example.greenspringboot.cust.controller;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class CustRegisterController {

    @Autowired
    CustService custService;

    @Autowired
    CustRepository custRepository;

    @GetMapping("/register")
    public String register() {
        return "registerForm";
    }

//    중복 이메일 체크
    @PostMapping("/emailCheckPost")
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

    @PostMapping("/register")
    public String registerPost(Cust cust, CustDto custDto, HttpServletRequest request, @RequestParam("emailCode") String userInputCode) {

        HttpSession session = request.getSession();
        String savedVerificationCode = (String) session.getAttribute("verificationCode");

        System.out.println("인증번호: " + savedVerificationCode);
        System.out.println("내가 입력한 값: " + userInputCode);

        if (savedVerificationCode != null && savedVerificationCode.equals(userInputCode)) {
//            비밀번호 유효성 검사
            custService.validatePassword(custDto.getcPwd());
//            비밀번호 해시화
            cust.setCPwd(custService.pwdEncrypt(custDto.getcPwd()));
            custRepository.save(cust);
            return "loginForm";
        }
        System.out.println("인증번호 잘못 입력하셨습니다.");
        return "errorPage";
    }
}
