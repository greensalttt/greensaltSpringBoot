package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.dto.CustDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@Service
public interface CustService {
    String emailCheck(String cEmail);

    String nickCheck(String cNick);

    Boolean save(Cust cust, CustDto custDto, HttpServletRequest request, @RequestParam("emailCode") String userInputCode);

    String joinEmail(String cEmail) throws Exception;
    String ResetEmail(String cEmail) throws Exception;

    Boolean forgotPwdCId(String cEmail, HttpServletRequest request);

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    Boolean login(String email, String rawPassword, HttpServletRequest request);

    boolean pwdChange(int cId, CustDto custDto, String curPwd);

    boolean forgotPwdChange(int cId, CustDto custDto);


    void updateSession(HttpSession session, CustDto custDto);

    boolean custModify(int cId, CustDto custDto, HttpSession session);


    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    CustDto toPwdDto(Cust cust);

    Cust toPwdEntity(Cust cust, CustDto custDto);

    void myPage(int cId, HttpServletRequest request);


}
