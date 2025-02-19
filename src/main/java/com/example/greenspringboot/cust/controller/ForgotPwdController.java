package com.example.greenspringboot.cust.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ForgotPwdController {



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
}
