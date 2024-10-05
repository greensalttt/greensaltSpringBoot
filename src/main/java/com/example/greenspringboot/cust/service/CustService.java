package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.dto.CustDto;

import java.util.Optional;

public interface CustService {
    String emailCheck(String cEmail);

    String nmCheck(String cNm);

    String joinEmail(String cEmail) throws Exception;

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    CustDto login(String email, String rawPassword);


}
