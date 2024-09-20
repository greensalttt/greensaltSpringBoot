package com.example.greenspringboot.cust;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
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
        CustDto custDto = new CustDto("1234q");

        custService.validatePassword(custDto.getcPwd());

        Cust cust = Cust.builder()
                .cEmail("test5@naver.com")
                .cPwd(custService.pwdEncrypt(custDto.getcPwd()))
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

//    @Test
//    public void crudTest() {
//        // DTO에서 비밀번호 유효성 검사 수행
//        CustDto custDto = new CustDto("1234q");
//
//        // 비밀번호 암호화
//        String encryptedPwd = custService.pwdEncrypt(custDto.getcPwd());
//
//        Cust cust = Cust.builder()
//                .cEmail("test@naver.com")
//                .cPwd(encryptedPwd) // 암호화된 비밀번호를 사용
//                .c_name("김김김")
//                .c_nm("김")
//                .c_birth("19900101")
//                .c_gnd('M')
//                .c_phn("123456789")
//                .c_zip("123456")
//                .c_road_a("Seoul Street")
//                .c_jibun_a("Seoul Jibun")
//                .c_det_a("Apartment 101")
//                .build();
//
//        custRepository.save(cust);
//    }

//    이메일이 중복이면 실패, 중복 아니면 성공
    @Test
    public void emailCheckTest() {
        boolean exists = custRepository.existsBycEmail("test@naver.com");
        assertFalse(exists, "fali");
    }
}
