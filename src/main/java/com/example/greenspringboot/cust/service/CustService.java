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

    void custHist(CustDto custDto, CustDto oldData);

//    CustDto convertToDto(Cust cust);
            ;
    void updateSession(HttpSession session, CustDto custDto);

    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    void pwdChange(int cId, String cPwd, CustDto oldPwd);

    CustDto toPwdDto(Cust cust);


    Cust toPwdEntity(Cust cust, CustDto custDto);


}
