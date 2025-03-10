package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.dto.CustDto;
//import com.example.greenspringboot.cust.dto.CustHistDto;
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

    // 회원가입 이메일
    @Override
    public String joinEmail(String cEmail) throws Exception {
        makeRandomNumber();
        String subject = "WELCOME GREEN :)";
        String content = "고객님이 요청하신 인증번호는 " + authNumber + "입니다.";
        sendEmail(cEmail, subject, content);
        return Integer.toString(authNumber);
    }

    // 비밀번호 찾기 이메일
    @Override
    public String joinEmail2(String cEmail) throws Exception {
        makeRandomNumber();
        String subject = "비밀번호 찾기 인증 메일입니다.";
        String content = "화면 인증번호 " + authNumber + "를 적어주세요.";
        sendEmail(cEmail, subject, content);
        return Integer.toString(authNumber);
    }

    @Transactional
    @Override
    public Boolean login(String cEmail, String cPwd, HttpServletRequest request) {
            Cust cust = custRepository.findBycEmail(cEmail);
            /*dto가 가져온 비밀번호와 내가 입력한 비밀번호와 같지 않다면 로그인 실패*/

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (!encoder.matches(cPwd, cust.getCPwd())) {
                return false;
        }

        CustDto custDto = CustDto.builder()  // 빌더 패턴을 사용하여 객체 생성
                .cId(cust.getCId())
                .cNick(cust.getCNick())
                .build();  // 필요한 데이터만 DTO로 추출

        HttpSession session = request.getSession();
            session.setAttribute("cId", custDto.getCId());
            session.setAttribute("cNick", custDto.getCNick());
            custRepository.incrementViSitCnt(cEmail);
        return true;
    }

//    로그인이 안된채로 이메일 인증만을 통해서 cId 세션에 저장시키기
    @Override
    public Boolean forgotPwdCId(String cEmail, HttpServletRequest request) {
        Cust cust = custRepository.findBycEmail(cEmail);

        if (cust == null) {
            throw new IllegalArgumentException("이메일로 고객을 찾을 수 없습니다.");  // 이메일로 고객을 찾지 못한 경우 예외 처리
        }

        CustDto custDto = CustDto.builder()  // 빌더 패턴을 사용하여 객체 생성
                .cId(cust.getCId())
                .build();  // 필요한 데이터만 DTO로 추출

        HttpSession session = request.getSession();
        session.setAttribute("cId", custDto.getCId());

        return true;
    }



    @Override
    public void myPage(int cId, HttpServletRequest request) {
        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // Optional에서 실제 Cust 객체를 꺼냄

            CustDto custDto = toDto(cust); // 엔티티를 DTO로 변환하여 반환

        HttpSession session = request.getSession();
        session.setAttribute("cEmail", custDto.getCEmail());
        session.setAttribute("cName", custDto.getCName());
        session.setAttribute("cZip", custDto.getCZip());
        session.setAttribute("cRoadA", custDto.getCRoadA());
        session.setAttribute("cJibunA", custDto.getCJibunA());
        session.setAttribute("cDetA", custDto.getCDetA());
        session.setAttribute("cPhn", custDto.getCPhn());
        session.setAttribute("cGnd", custDto.getCGnd());
        session.setAttribute("cBirth", custDto.getCBirth());
        session.setAttribute("smsAgr", custDto.getSmsAgr());
        session.setAttribute("emailAgr", custDto.getEmailAgr());
        session.setAttribute("visitCnt", custDto.getVisitCnt());

        Date regDate = custDto.getRegDt();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String regDateStr = dateFormat.format(regDate);

        session.setAttribute("regDt", regDateStr);
        } else {
            // Cust가 없을 경우의 처리 (예: 에러 메시지나 기본값 설정 등)
            System.out.println("고객 정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public boolean custModify(int cId, CustDto custDto, HttpSession session) {
        Optional<Cust> optionalCust = custRepository.findById(cId);
        if (optionalCust.isPresent()) {
            Cust oldCust = optionalCust.get();
            CustDto oldData = toDto(oldCust);

            // 데이터 설정
            custDto.setCId(cId);

            // 기존 dto를 엔티티로 변환하고 정보 저장
            toEntity(oldCust, custDto);
            custRepository.save(oldCust);

            // 이력 기록
            List<CustHist> custHistList = new ArrayList<>();
            addCustHist(custHistList, custDto, "NICK", oldData.getCNick(), custDto.getCNick());
            addCustHist(custHistList, custDto, "ZIP", oldData.getCZip(), custDto.getCZip());
            addCustHist(custHistList, custDto, "ROAD", oldData.getCRoadA(), custDto.getCRoadA());
            addCustHist(custHistList, custDto, "JIBUN", oldData.getCJibunA(), custDto.getCJibunA());
            addCustHist(custHistList, custDto, "DET", oldData.getCDetA(), custDto.getCDetA());
            addCustHist(custHistList, custDto, "PHN", oldData.getCPhn(), custDto.getCPhn());
            addCustHist(custHistList, custDto, "BIRTH", oldData.getCBirth(), custDto.getCBirth());
            addCustHist(custHistList, custDto, "SMS", oldData.getSmsAgr(), custDto.getSmsAgr());
            addCustHist(custHistList, custDto, "EMAIL", oldData.getEmailAgr(), custDto.getEmailAgr());

            for (CustHist custHist : custHistList) {
                // 이력 저장
                custHistRepository.save(custHist);
            }

            // 세션 업데이트
            updateSession(session, custDto);

            return true;
        } else {
            return false;  // 고객이 없을 경우
        }
    }

    private void addCustHist(List<CustHist> custHistList, CustDto newData,
                             String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            CustHist custHist = new CustHist();
            custHist.setCId(newData.getCId());
            custHist.setCCngCd(changeCode);
            custHist.setCBf(oldValue);
            custHist.setCAf(newValue);
            custHistList.add(custHist);
        }
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

//    @Transactional
//    @Override
//    public void pwdChange(int cId, CustDto custDto, CustDto oldPwd) {
//
//        Optional<Cust> optionalCust = custRepository.findById(cId);
//
//        if (optionalCust.isPresent()) {
//            Cust cust = optionalCust.get(); // Optional에서 실제 Cust 객체를 꺼냄
//
//            toPwdEntity(cust, custDto);
//            custRepository.save(cust);
//
//            CustHist custHist = new CustHist();
//            custHist.setCId(custDto.getCId());
//            custHist.setCCngCd("PWD");
//            custHist.setCBf(oldPwd.getCPwd());
//            custHist.setCAf(pwdEncrypt(custDto.getCPwd()));
//
//            custHistRepository.save(custHist);
//        }else{
//            System.out.println("고객 정보를 찾을 수 없습니다");
//        }
//    }

    @Transactional
    @Override
    public boolean pwdChange(int cId, CustDto custDto, String curPwd) {
        // 고객 정보 조회
        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // 고객 객체를 꺼냄
            CustDto oldPwd = toPwdDto(cust); // 기존 비밀번호 DTO로 변환

            // 현재 비밀번호와 입력한 비밀번호 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(curPwd, oldPwd.getCPwd())) {
                return false; // 비밀번호 불일치
            }

            // 비밀번호 변경
            custDto.setCId(cId);
            toPwdEntity(cust, custDto);
            custRepository.save(cust); // 비밀번호 업데이트

            // 이력 기록
            CustHist custHist = new CustHist();
            custHist.setCId(custDto.getCId());
            custHist.setCCngCd("PWD");
            custHist.setCBf(oldPwd.getCPwd());
            custHist.setCAf(pwdEncrypt(custDto.getCPwd()));

            custHistRepository.save(custHist); // 이력 저장

            return true; // 성공적으로 비밀번호 변경
        } else {
            System.out.println("고객 정보를 찾을 수 없습니다");
            return false; // 고객을 찾을 수 없으면 실패
        }
    }


    @Override
    public void updateSession(HttpSession session, CustDto custDto) {
        session.setAttribute("cNick", custDto.getCNick());
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
                .cPwd(cust.getCPwd())
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
                .visitCnt(cust.getVisitCnt())
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