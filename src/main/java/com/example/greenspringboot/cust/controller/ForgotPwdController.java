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

@Controller
public class ForgotPwdController {


    @Autowired
    private CustService custService;

    /*
    1. 가입되있는 이메일인지 확인
    2. 그 이메일로 인증번호 보냄
    2. 인증번호 확인 후 비밀번호 변경
    3. 변경된 비밀번호로 로그인
    */
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


    @GetMapping("/forgotPwd2")
    public String forgotPwd2() {
        return "forgotPwd2";
    }
}
