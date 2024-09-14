package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.repository.CustRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    private JavaMailSenderImpl mailSender;

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
//        111111부터 999999까지 랜덤 난수 생성
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
//        생성된 변수를 authNumber에 저장
        authNumber = checkNum;
    }

    public void mailSend(String setFrom, String toMail, String title, String content) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        helper.setFrom(setFrom);
        helper.setTo(toMail);
        helper.setSubject(title);
        // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
        helper.setText(content, true);
        mailSender.send(message);
    }

    public String joinEmail(String cEmail) throws Exception {
        makeRandomNumber();
        String setFrom = "homerunball46g@gmail.com";
        String toMail = cEmail;
        String title = "WELCOME GREEN :)"; // 이메일 제목
        String content = "고객님이 요청하신 인증번호는 " + authNumber + "입니다.";
        mailSend(setFrom, toMail, title, content);
        return Integer.toString(authNumber);
    }

}