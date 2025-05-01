package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.dto.CustDto;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CustService {
    String emailCheck(String cEmail);

    String nickCheck(String cNick);

    Boolean save(Cust cust, HttpServletRequest request, @RequestParam("emailCode") String userInputCode);

    String joinEmail(String cEmail) throws Exception;
    String ResetEmail(String cEmail) throws Exception;

    Boolean forgotPwdCId(String cEmail, HttpServletRequest request);

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    Boolean login(String email, String rawPassword, HttpServletRequest request);

    boolean pwdChange(int cId, CustDto custDto, String curPwd);

    boolean forgotPwdChange(int cId, CustDto custDto);


    void updateSession(HttpSession session, CustDto custDto);


    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    CustDto toPwdDto(Cust cust);

    Cust toPwdEntity(Cust cust, CustDto custDto);

    void myPage(int cId, Model model);
     void myPageInfo(int cId, Model model);

    boolean custModify(int cId, CustDto custDto);




}
