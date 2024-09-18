package com.example.greenspringboot.cust;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class CustRepositoryTest {

    @Autowired private CustRepository custRepository;

    @Test
    public void crudTest() {
        Cust cust = Cust.builder()
                .cEmail("test@naver.com")
                .cPwd("password")
                .c_name("김김김")
                .c_nm("김")
                .c_birth("19900101")
                .c_gnd('M')
                .c_phn("123456789")
                .c_zip("123456")
                .c_road_a("Seoul Street")
                .c_jibun_a("Seoul Jibun")
                .c_det_a("Apartment 101")
                .build();

        custRepository.save(cust);
    }

//    이메일이 중복이면 실패, 중복 아니면 성공
    @Test
    public void emailCheckTest() {
        boolean exists = custRepository.existsBycEmail("test@naver.com");
        assertFalse(exists, "fali");
    }
}
