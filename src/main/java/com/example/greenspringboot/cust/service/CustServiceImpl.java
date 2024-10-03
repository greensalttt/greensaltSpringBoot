package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustServiceImpl implements CustService{

    private final CustRepository custRepository;

    @Autowired
    private JavaMailSender mailSender;

    private int authNumber;

    @Override
    public String emailCheck(String cEmail) {
        if(!custRepository.existsBycEmail(cEmail)) {
//            AjAX emailgood의 응답이랑 서비스 리턴값이랑 서로 일치해야됨
            return "ok";
        } else {
            return "fail";
        }
    }

    public void makeRandomNumber() throws Exception {
//        랜덤 객체 생성
        Random r = new Random();
        authNumber = 100000 + r.nextInt(900000);
        System.out.println("서비스에서 받아온 인증번호 : " + authNumber);
    }


private void sendEmail(String toMail, String subject, String content) throws Exception {
    MimeMessage message = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
    helper.setFrom("homerunball46g@gmail.com"); // 상수 사용
    helper.setTo(toMail);
    helper.setSubject(subject);
    helper.setText(content, true); // HTML 형식으로 설정
    mailSender.send(message);
}

public String joinEmail(String cEmail) throws Exception {
    makeRandomNumber();
    String subject = "WELCOME GREEN :)";
    String content = "고객님이 요청하신 인증번호는 " + authNumber + "입니다.";
    sendEmail(cEmail, subject, content);
    return Integer.toString(authNumber);
}

    public String pwdEncrypt(String cPwd){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(cPwd);
    }

//    비밀번호 서버 유효성 검사
//    해쉬화 같은 복잡한 것들은 서비스로 빼서 엔티티로 받기 전에 미리 유효성 검사 진행
    public void validatePassword(String cPwd) {
        if (!cPwd.matches("^(?=.*\\d)(?=.*[a-z])[a-z0-9]{4,15}$")) {
            throw new IllegalArgumentException("비밀번호는 4~12자로 영어와 소문자 숫자를 포함해야 합니다.");
        }
    }


    public CustDto login(String email, String rawPassword) {
        Optional<Cust> custOptional = custRepository.findBycEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (custOptional.isPresent() && passwordEncoder.matches(rawPassword, custOptional.get().getCPwd())) {
            Cust cust = custOptional.get();
            CustDto custDto = new CustDto();
//        엔티티로 DB값을 가져온 후 클라이언트에서 필요한 컬럼만 DTO로 따로 저정해서 컨트롤러로 이동
            custDto.setCId(cust.getCId());
            custDto.setCEmail(cust.getCEmail());
            custDto.setCName(cust.getCName());
            custDto.setCNm(cust.getCNm());
            custDto.setCBirth(cust.getCBirth());
            custDto.setCGnd(cust.getCGnd());
            custDto.setCPhn(cust.getCPhn());
            custDto.setCZip(cust.getCZip());
            custDto.setCRoadA(cust.getCRoadA());
            custDto.setCJibunA(cust.getCJibunA());
            custDto.setCDetA(cust.getCDetA());
            custDto.setSmsAgr(String.valueOf(cust.getSmsAgr()));
            custDto.setEmailAgr(String.valueOf(cust.getEmailAgr()));
            custDto.setRegDt(cust.getRegDt());
            return custDto; // DTO 반환
        }
        return null;
    }

}