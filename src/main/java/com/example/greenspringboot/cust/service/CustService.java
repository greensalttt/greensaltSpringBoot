package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.dto.CustDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface CustService {
    String emailCheck(String cEmail);

    String nickCheck(String cNick);

    String joinEmail(String cEmail) throws Exception;

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    Boolean login(String email, String rawPassword, HttpServletRequest request);

    void custModify(int cId, CustDto custDto, CustDto oldData);
//
    boolean pwdChange(int cId, CustDto custDto, String curPwd);

    void updateSession(HttpSession session, CustDto custDto);

    boolean updateCustInfo(int cId, CustDto custDto, HttpSession session);


    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    CustDto toPwdDto(Cust cust);

    Cust toPwdEntity(Cust cust, CustDto custDto);

    void myPage(int cId, HttpServletRequest request);


}
