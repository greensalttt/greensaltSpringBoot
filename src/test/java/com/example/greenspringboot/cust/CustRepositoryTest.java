package com.example.greenspringboot.cust;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.cust.dto.CustDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustRepositoryTest {

    @Autowired private CustRepository custRepository;

    @Autowired private CustService custService;


    @Test
    public void crudTest() {
        CustDto custDto = CustDto.builder()
                .cId(123)
                .cPwd("123456789q")
                .build();


        custService.validatePassword(custDto.getCPwd());

        Cust cust = Cust.builder()
                .cEmail("test11@naver.com")
                .cPwd(custService.pwdEncrypt(custDto.getCPwd()))
                .cName("아아")
                .cNick("김")
                .cBirth("19900101")
                .cGnd("M")
                .cPhn("123456789")
                .cZip("123456")
                .cRoadA("Seoul Street")
                .cJibunA("Seoul Jibun")
                .cDetA("Apartment 101")
                .build();
        custRepository.save(cust);
    }

//    이메일이 중복이면 실패, 중복 아니면 성공
    @Test
    public void emailCheckTest() {
        boolean exists = custRepository.existsBycEmail("test@naver.com");
        assertFalse(exists, "fali");
    }

    @Test
    public void nickCheckTest() {
        boolean exists = custRepository.existsBycNick("아이스2");
        assertFalse(exists, "fali");
    }

}
