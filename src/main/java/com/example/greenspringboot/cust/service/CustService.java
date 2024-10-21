package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.dto.CustDto;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public interface CustService {
    String emailCheck(String cEmail);

    String nickCheck(String cNick);

    String joinEmail(String cEmail) throws Exception;

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    CustDto login(String email, String rawPassword);

    void custHist(CustDto custDto, CustDto oldData);

//    CustDto convertToDto(Cust cust);
            ;
    void updateSession(HttpSession session, CustDto custDto);

    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    void pwdChange(CustDto custDto, CustDto oldData);

}
