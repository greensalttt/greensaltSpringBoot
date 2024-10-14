package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.dto.CustDto;
import com.example.greenspringboot.dto.CustHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CustServiceImpl implements CustService {

    private final CustRepository custRepository;
    private final CustHistRepository custHistRepository;


    @Autowired
    private JavaMailSender mailSender;

    //    일반 필드는 오토와이어드 필요없음
    private int authNumber;


    @Override
    public String emailCheck(String cEmail) {
        if (!custRepository.existsBycEmail(cEmail)) {
//            AjAX emailgood의 응답이랑 서비스 리턴값이랑 서로 일치해야됨
            return "ok";
        } else {
            return "fail";
        }
    }

    @Override
    public String nickCheck(String cNick) {
        if (!custRepository.existsBycNick(cNick)) {
//            AjAX와 응답이랑 서비스 리턴값이랑 서로 일치해야됨
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

    public String pwdEncrypt(String cPwd) {
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
//        값이 있을수도 없을수도 있을때 옵셔널로 처리
        Optional<Cust> custOptional = custRepository.findBycEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (custOptional.isPresent() && passwordEncoder.matches(rawPassword, custOptional.get().getCPwd())) {
            Cust cust = custOptional.get();
            return convertToDto(cust); // 엔티티를 DTO로 변환하여 반환
        }
        return null;
    }

    public CustDto convertToDto(Cust cust) {
        CustDto custDto = new CustDto();
        custDto.setCId(cust.getCId());
        custDto.setCEmail(cust.getCEmail());
        custDto.setCName(cust.getCName());
        custDto.setCNick(cust.getCNick());
        custDto.setCBirth(cust.getCBirth());
        custDto.setCGnd(cust.getCGnd());
        custDto.setCPhn(cust.getCPhn());
        custDto.setCZip(cust.getCZip());
        custDto.setCRoadA(cust.getCRoadA());
        custDto.setCJibunA(cust.getCJibunA());
        custDto.setCDetA(cust.getCDetA());
        custDto.setSmsAgr(cust.getSmsAgr());
        custDto.setEmailAgr(cust.getEmailAgr());
        custDto.setRegDt(cust.getRegDt());
        return custDto;
    }

//    public void custHist(CustDto custDto) {
//        // 기존 회원 정보 조회
//      Optional<Cust> optionalCust = custRepository.findBycId(custDto.getCId());  // custDto에서 CId 값을 받아와야 함
//
//        System.out.println("서비스에서 CId: " + custDto.getCId());  // CId 값 확인
//
////        isPresent() = 값이 있는지 없는지 확인
//        if (optionalCust.isPresent()) {
//            // 있으면 get() 으로 가져온다
//            Cust cust = optionalCust.get();
//
//            cust.setCZip(custDto.getCZip());
//            cust.setCRoadA(custDto.getCRoadA());
//            cust.setCJibunA(custDto.getCJibunA());
//            cust.setCDetA(custDto.getCDetA());
//            cust.setCPhn(custDto.getCPhn());
//            cust.setCBirth(custDto.getCBirth());
//            cust.setSmsAgr(custDto.getSmsAgr());
//            cust.setEmailAgr(custDto.getEmailAgr());
//
//            custRepository.save(cust);
//        }
//    }

    public void custHist(CustDto custDto, CustDto oldData) {
        // 기존 회원 정보 조회
        Optional<Cust> optionalCust = custRepository.findBycId(custDto.getCId());  // custDto에서 CId 값을 받아와야 함

        System.out.println("서비스에서 CId: " + custDto.getCId());  // CId 값 확인
        ////        isPresent() = 값이 있는지 없는지 확인
        if (optionalCust.isPresent()) {
            // 있으면 get() 으로 가져온다
            Cust cust = optionalCust.get();

            cust.setCZip(custDto.getCZip());
            cust.setCRoadA(custDto.getCRoadA());
            cust.setCJibunA(custDto.getCJibunA());
            cust.setCDetA(custDto.getCDetA());
            cust.setCPhn(custDto.getCPhn());
            cust.setCBirth(custDto.getCBirth());
            cust.setSmsAgr(custDto.getSmsAgr());
            cust.setEmailAgr(custDto.getEmailAgr());

            custRepository.save(cust);
        }

        List<CustHistDto> custHistList = new ArrayList<>();

        addCustHist(custHistList, custDto, oldData, "ZIP", oldData.getCZip(), custDto.getCZip());
        addCustHist(custHistList, custDto, oldData, "ROAD", oldData.getCRoadA(), custDto.getCRoadA());
        addCustHist(custHistList, custDto, oldData, "DET_A", oldData.getCDetA(), custDto.getCDetA());
        addCustHist(custHistList, custDto, oldData, "PHN", oldData.getCPhn(), custDto.getCPhn());
        addCustHist(custHistList, custDto, oldData, "BIRTH", oldData.getCBirth(), custDto.getCBirth());
        addCustHist(custHistList, custDto, oldData, "SMS", oldData.getSmsAgr(), custDto.getSmsAgr());
        addCustHist(custHistList, custDto, oldData, "EMAIL", oldData.getEmailAgr(), custDto.getEmailAgr());

//        for (CustHistDto custHistDto : custHistList) {
//            custHistRepository.save(CustHist);
//        }

        for (CustHistDto custHistDto : custHistList) {
            // CustHistDto를 CustHist 엔티티로 변환
            CustHist custHist = new CustHist();
            custHist.setCId(custHistDto.getCId());
            custHist.setCCngCd(custHistDto.getCCngCd());
            custHist.setCBf(custHistDto.getCBf());
            custHist.setCAf(custHistDto.getCAf());

            // 이력 저장
            custHistRepository.save(custHist);  // custHist 엔티티 인스턴스를 저장
        }
    }

    private void addCustHist(List<CustHistDto> custHistList, CustDto newData, CustDto oldData,
                             String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            CustHistDto custHistDto = new CustHistDto();
            custHistDto.setCId(newData.getCId());
            custHistDto.setCCngCd(changeCode);
            custHistDto.setCBf(oldValue);
            custHistDto.setCAf(newValue);
            custHistList.add(custHistDto);
        }
    }
}