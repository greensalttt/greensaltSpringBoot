package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
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
        System.out.println("인증번호 : " + authNumber);
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
}