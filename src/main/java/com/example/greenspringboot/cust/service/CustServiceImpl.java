package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.dto.CustHistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@RequiredArgsConstructor
public class CustServiceImpl implements CustService {

    @Autowired
    private CustRepository custRepository;

    @Autowired
    private CustHistRepository custHistRepository;


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
    @Override
    public String joinEmail(String cEmail) throws Exception {
        makeRandomNumber();
        String subject = "WELCOME GREEN :)";
        String content = "고객님이 요청하신 인증번호는 " + authNumber + "입니다.";
        sendEmail(cEmail, subject, content);
        return Integer.toString(authNumber);
    }
    @Override
    public String pwdEncrypt(String cPwd) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(cPwd);
    }

    //    비밀번호 서버 유효성 검사
//    해쉬화 같은 복잡한 것들은 서비스로 빼서 엔티티로 받기 전에 미리 유효성 검사 진행
    @Override
    public void validatePassword(String cPwd) {
        if (!cPwd.matches("^(?=.*\\d)(?=.*[a-z])[a-z0-9]{4,15}$")) {
            throw new IllegalArgumentException("비밀번호는 4~12자로 영어와 소문자 숫자를 포함해야 합니다.");
        }
    }
    @Override
    public Boolean login(String cEmail, String cPwd, HttpServletRequest request) {
            /*db에 있는 이메일을 Dto에 대입*/
            Cust cust = custRepository.findBycEmail(cEmail);
            /*dto가 가져온 비밀번호와 내가 입력한 비밀번호와 같지 않다면 로그인 실패*/

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(cPwd, cust.getCPwd())) {
                return false;
        }
        /*예외 없으면 로그인 성공*/
            HttpSession session = request.getSession();
            session.setAttribute("cId", cust.getCId());
            toDto(cust); // 엔티티를 DTO로 변환하여 반환
        return true;
    }

    @Override
    public void myPage(int cId, HttpServletRequest request){
        Cust cust = custRepository.findBycId(cId);
        CustDto custDto = toDto(cust); // 엔티티를 DTO로 변환하여 반환

        HttpSession session = request.getSession();
        session.setAttribute("cName", custDto.getCName());
        session.setAttribute("cNick", custDto.getCNick());

        Date regDate = custDto.getRegDt();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String regDateStr = dateFormat.format(regDate);

        session.setAttribute("regDt", regDateStr);
    }


//    @Transactional
//    @Override
//    public void custHist(CustDto custDto, CustDto oldData) {
//        // 기존 회원 정보 조회
//        Optional<Cust> optionalCust = custRepository.findBycId(custDto.getCId());  // custDto에서 CId 값을 받아와야 함
//
//        System.out.println("개인정보 변경 서비스에서 CId: " + custDto.getCId());  // CId 값 확인
//        //        isPresent() = 값이 있는지 없는지 확인
//        if (optionalCust.isPresent()) {
//            // 있으면 get() 으로 가져온다
//            Cust cust = optionalCust.get();
//            toEntity(cust, custDto);
//
//            custRepository.save(cust);
//        }
//
//        List<CustHistDto> custHistList = new ArrayList<>();
//
//        addCustHist(custHistList, custDto, oldData, "ZIP", oldData.getCZip(), custDto.getCZip());
//        addCustHist(custHistList, custDto, oldData, "ROAD", oldData.getCRoadA(), custDto.getCRoadA());
//        addCustHist(custHistList, custDto, oldData, "JIBUN", oldData.getCJibunA(), custDto.getCJibunA());
//        addCustHist(custHistList, custDto, oldData, "DET", oldData.getCDetA(), custDto.getCDetA());
//        addCustHist(custHistList, custDto, oldData, "PHN", oldData.getCPhn(), custDto.getCPhn());
//        addCustHist(custHistList, custDto, oldData, "BIRTH", oldData.getCBirth(), custDto.getCBirth());
//        addCustHist(custHistList, custDto, oldData, "SMS", oldData.getSmsAgr(), custDto.getSmsAgr());
//        addCustHist(custHistList, custDto, oldData, "EMAIL", oldData.getEmailAgr(), custDto.getEmailAgr());
//
//        for (CustHistDto custHistDto : custHistList) {
//            // CustHistDto를 CustHist 엔티티로 변환
//            CustHist custHist = new CustHist();
//            custHist.setCId(custHistDto.getCId());
//            custHist.setCCngCd(custHistDto.getCCngCd());
//            custHist.setCBf(custHistDto.getCBf());
//            custHist.setCAf(custHistDto.getCAf());
//
//            // 이력 저장
//            custHistRepository.save(custHist);
//        }
//    }

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


//    @Transactional
//    @Override
//    public void pwdChange(int cId, String cPwd, CustDto oldPwd) {
//        CustDto custDto = new CustDto(cId, cPwd);
//        Optional<Cust> optionalCust = custRepository.findBycId(custDto.getCId());  // custDto에서 CId 값을 받아와야 함
//
//        System.out.println("비밀번호 변경 서비스에서 CId: " + custDto.getCId());  // CId 값 확인
//        ////        isPresent() = 값이 있는지 없는지 확인
//        if (optionalCust.isPresent()) {
//            // 있으면 get() 으로 가져온다
//            Cust cust = optionalCust.get();
//            toPwdEntity(cust, custDto);
//            custRepository.save(cust);
//        }
//        CustHist custHist = new CustHist();
//        custHist.setCId(custDto.getCId());
//        custHist.setCCngCd("PWD");
//        custHist.setCBf(oldPwd.getCPwd());
//        custHist.setCAf(pwdEncrypt(custDto.getCPwd()));
//
//        custHistRepository.save(custHist);
//    }

    @Override
    public void updateSession(HttpSession session, CustDto custDto) {
        session.setAttribute("cZip", custDto.getCZip());
        session.setAttribute("cRoadA", custDto.getCRoadA());
        session.setAttribute("cJibunA", custDto.getCJibunA());
        session.setAttribute("cDetA", custDto.getCDetA());
        session.setAttribute("cPhn", custDto.getCPhn());
        session.setAttribute("cBirth", custDto.getCBirth());
        session.setAttribute("smsAgr", custDto.getSmsAgr());
        session.setAttribute("emailAgr", custDto.getEmailAgr());
    }

    @Override
    public CustDto toDto(Cust cust) {
        return CustDto.builder()
                .cId(cust.getCId())
                .cEmail(cust.getCEmail())
                .cName(cust.getCName())
                .cNick(cust.getCNick())
                .cBirth(cust.getCBirth())
                .cGnd(cust.getCGnd())
                .cPhn(cust.getCPhn())
                .cZip(cust.getCZip())
                .cRoadA(cust.getCRoadA())
                .cJibunA(cust.getCJibunA())
                .cDetA(cust.getCDetA())
                .smsAgr(cust.getSmsAgr())
                .emailAgr(cust.getEmailAgr())
                .regDt(cust.getRegDt())
                .build(); // 빌더를 사용해 객체 생성
    }


    // 객체를 매개변수로 넣으면 개인정보 수정에서 하나만 수정해도 전체 수정이 되기떄문에 변경이 있다는 것만 수정해줄 수 있도록 if문이 필요함
    // 객체를 새로 생성할 경우에는 하나만 수정해도 문제 없지만 객체를 새로 만드는만큼 성능에 부담을 줄 수가 있다.
    @Override
    public void toEntity(Cust cust, CustDto custDto) {
        if (custDto.getCEmail() != null) {
            cust.setCEmail(custDto.getCEmail());
        }
        if (custDto.getCName() != null) {
            cust.setCName(custDto.getCName());
        }
        if (custDto.getCNick() != null) {
            cust.setCNick(custDto.getCNick());
        }
        if (custDto.getCGnd() != null) {
            cust.setCGnd(custDto.getCGnd());
        }
        if (custDto.getCPhn() != null) {
            cust.setCPhn(custDto.getCPhn());
        }
        if (custDto.getCZip() != null) {
            cust.setCZip(custDto.getCZip());
        }
        if (custDto.getCRoadA() != null) {
            cust.setCRoadA(custDto.getCRoadA());
        }
        if (custDto.getCJibunA() != null) {
            cust.setCJibunA(custDto.getCJibunA());
        }
        if (custDto.getCDetA() != null) {
            cust.setCDetA(custDto.getCDetA());
        }
        if (custDto.getSmsAgr() != null) {
            cust.setSmsAgr(custDto.getSmsAgr());
        }
        if (custDto.getEmailAgr() != null) {
            cust.setEmailAgr(custDto.getEmailAgr());
        }
    }

    @Override
    public CustDto toPwdDto(Cust cust) {
        return CustDto.builder()
        .cPwd(cust.getCPwd())
        .build(); // 빌더를 사용해 객체 생성
    }


    @Override
    public Cust toPwdEntity(Cust cust, CustDto custDto) {
    cust.setCPwd(pwdEncrypt(custDto.getCPwd())); // custDto의 비밀번호를 암호화하여 cust 객체에 설정
    return cust;
}

}