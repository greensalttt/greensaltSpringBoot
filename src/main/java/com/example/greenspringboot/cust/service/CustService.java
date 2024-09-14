package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.dto.CustDto;

public interface CustService {
    String emailCheck(String cEmail);

    String joinEmail(String cEmail) throws Exception;

}
