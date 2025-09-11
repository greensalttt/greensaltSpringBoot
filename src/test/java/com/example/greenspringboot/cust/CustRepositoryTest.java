package com.example.greenspringboot.cust;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CustRepositoryTest {

    @Autowired private CustRepository custRepository;

    @Autowired private CustService custService;

    @Autowired
    private OrderRepository orderRepository;

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
                .cNick("김김김")
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


    @Test
    public void pwd(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPwd = "1234";
        String encodedPwd = encoder.encode(rawPwd);
        System.out.println("해시값: "+encodedPwd);
    }


    @Test
    public void checkPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String raw = "12345";
        String hash = "$2a$10$AyJUTr2rXuY4jUe2RpGVReCD4/ylIak5/TyDG7GdHLvbjVTEpbuXC";

        assertTrue(encoder.matches(raw, hash));
    }


    @Test
    public void testFindMyReservations() {
        Integer cId = 5; // 실제 존재하는 cId로 테스트

        List<MyReservationDto> list = orderRepository.findMyReservations(cId);

        System.out.println("==== 예매 내역 수: " + list.size());
        for (MyReservationDto dto : list) {
            System.out.println(dto);
        }

        assertFalse(list.isEmpty(), "예매 내역이 존재해야 합니다.");
    }
}
