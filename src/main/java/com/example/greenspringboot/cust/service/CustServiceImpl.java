package com.example.greenspringboot.cust.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.dto.CustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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
    public String ResetEmail(String cEmail) throws Exception {
        makeRandomNumber();
        String subject = "비밀번호 찾기 인증 메일입니다.";
        String content = "화면 인증번호 " + authNumber + "를 적어주세요.";
        sendEmail(cEmail, subject, content);
        return Integer.toString(authNumber);
    }

    @Override
    public Boolean save(@Valid Cust cust, HttpServletRequest request, @RequestParam("emailCode") String userInputCode) {
        HttpSession session = request.getSession();
        String savedVerificationCode = (String) session.getAttribute("verificationCode");

        System.out.println("인증번호: " + savedVerificationCode);
        System.out.println("내가 입력한 값: " + userInputCode);

        if (savedVerificationCode != null && savedVerificationCode.equals(userInputCode)) {
            validatePassword(cust.getCPwd());
            cust.setCPwd(pwdEncrypt(cust.getCPwd()));
            custRepository.save(cust);
            return true;
        }
        System.out.println("인증번호 잘못 입력하셨습니다.");
        return false;
    }

    @Transactional
    @Override
    public Boolean login(String cEmail, String cPwd, HttpServletRequest request) {

        System.out.println("로그인 이메일 값: " + cEmail); // 이메일 값 찍어보기

            Cust cust = custRepository.findBycEmail(cEmail);

        if (cust == null) {
            return false; // 이메일 없으면 로그인 실패 처리
        }

        if ("D".equals(cust.getCStatCd())) {
            return false;
        }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            평문화와 암호화 비밀번호를 비교할 수 있는 메서드
                if (!encoder.matches(cPwd, cust.getCPwd())) {
                return false;
        }

        CustDto custDto = CustDto.builder()  // 빌더 패턴을 사용하여 객체 생성
                .cId(cust.getCId())
                .build();  // 필요한 데이터만 DTO로 추출

        HttpSession session = request.getSession();
            session.setAttribute("cId", custDto.getCId());
            custRepository.incrementViSitCnt(cEmail);
        return true;
    }

//    로그인이 안된채로 이메일 인증만을 통해서 cId 세션에 저장시키기
    @Override
    public Boolean forgotPwdCId(String cEmail, HttpServletRequest request) {

        System.out.println("이메일 값: " + cEmail); // 이메일 값 찍어보기


        Cust cust = custRepository.findBycEmail(cEmail);

        System.out.println("찾으려는 객체: " + cust); // 로그로 객체 찍어보기

        // cust가 계속 null이 뜨는 이유가 뭘까
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
    public void myPage(int cId, Model model) {
        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // Optional에서 실제 Cust 객체를 꺼냄

            String cName = cust.getCName();
            String cNick = cust.getCNick();
            Date regDt = cust.getRegDt();
            Long visitCnt = cust.getVisitCnt();

            CustDto custDto = CustDto.builder()
                    .cName(cName)
                    .cNick(cNick)
                    .visitCnt(visitCnt)
                    .regDt(regDt)
                    .build();

            model.addAttribute("custDto", custDto);

            System.out.println("custDto:" + custDto);

        } else {
            // Cust가 없을 경우의 처리 (예: 에러 메시지나 기본값 설정 등)
            System.out.println("고객 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public void myPageInfo(int cId, Model model){

        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // Optional에서 실제 Cust 객체를 꺼냄

            String cEmail = cust.getCEmail();
            String cName = cust.getCName();
            String cNick = cust.getCNick();
            String cZip = cust.getCZip();
            String cRoadA = cust.getCRoadA();
            String cJibunA = cust.getCJibunA();
            String cDetA = cust.getCDetA();
            String cPhn = cust.getCPhn();
            String cGnd = cust.getCGnd();
            String cBirth = cust.getCBirth();
            String smsAgr = cust.getSmsAgr();
            String emailAgr = cust.getEmailAgr();
            Date regDt = cust.getRegDt();
            long visitCnt = cust.getVisitCnt();

            CustDto custDto = CustDto.builder()
                    .cEmail(cEmail)
                    .cName(cName)
                    .cNick(cNick)
                    .cZip(cZip)
                    .cRoadA(cRoadA)
                    .cJibunA(cJibunA)
                    .cDetA(cDetA)
                    .cPhn(cPhn)
                    .cGnd(cGnd)
                    .cBirth(cBirth)
                    .smsAgr(smsAgr)
                    .emailAgr(emailAgr)
                    .visitCnt(visitCnt)
                    .regDt(regDt)
                    .build();
            model.addAttribute("custDto", custDto);

            System.out.println("custDto:" + custDto);

        } else {
            // Cust가 없을 경우의 처리 (예: 에러 메시지나 기본값 설정 등)
            System.out.println("고객 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public boolean custModify(int cId, CustDto custDto) {
        Optional<Cust> optionalCust = custRepository.findById(cId);
        if (optionalCust.isPresent()) {
            Cust oldCust = optionalCust.get();
            CustDto oldData = toDto(oldCust);

            // ID 세팅
            custDto.setCId(cId);

            // 수정된 정보로 엔티티 덮어쓰기 및 저장
            toEntity(oldCust, custDto);
            custRepository.save(oldCust);

            // 변경 이력 기록
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

            // 이력 저장
            for (CustHist custHist : custHistList) {
                custHistRepository.save(custHist);
            }

            return true;
        }
        return false;
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

    @Transactional
    @Override
    public boolean pwdChange(int cId, CustDto custDto, String curPwd) {

        // 고객 정보 조회
        // Optional을 사용하면 내부에서 null 체크 생략 가능 > 자동 null 체크
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

@Transactional
@Override
public boolean forgotPwdChange(int cId, CustDto custDto) {
    // 고객 정보 조회
    Optional<Cust> optionalCust = custRepository.findById(cId);

    if (optionalCust.isPresent()) {
        Cust cust = optionalCust.get(); // 고객 객체를 꺼냄
        CustDto oldPwd = toPwdDto(cust); // 기존 비밀번호 DTO로 변환

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 새 비밀번호가 기존 비밀번호와 같으면 실패
        if (!encoder.matches(custDto.getCPwd(), oldPwd.getCPwd())) {
            // 새 비밀번호와 기존 비밀번호가 다르면 비밀번호 업데이트
            custDto.setCId(cId);
            toPwdEntity(cust, custDto);
            custRepository.save(cust);

            // 이력 기록
            CustHist custHist = new CustHist();
            custHist.setCId(custDto.getCId());
            custHist.setCCngCd("PWD");
            custHist.setCBf(oldPwd.getCPwd());
            custHist.setCAf(pwdEncrypt(custDto.getCPwd()));

            custHistRepository.save(custHist); // 이력 저장

            return true; // 성공적으로 비밀번호 변경
        } else {
            System.out.println("새 비밀번호가 기존 비밀번호와 같습니다.");
            return false; // 실패: 새 비밀번호가 기존 비밀번호와 같으면 변경하지 않음
        }
    } else {
        System.out.println("고객 정보를 찾을 수 없습니다");
        return false; // 고객을 찾을 수 없으면 실패
    }
}

    @Transactional
    @Override
    public boolean custDrop(int cId, String dropPwd){

        // 고객 정보 조회
        // Optional을 사용하면 내부에서 null 체크 생략 가능 > 자동 null 체크
        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // 고객 객체를 꺼냄
            CustDto oldCust = toPwdDto2(cust); // 기존 비밀번호 DTO로 변환

            // 현재 비밀번호와 입력한 비밀번호 비교
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(dropPwd, oldCust.getCPwd())) {
                System.out.println("비밀번호가 불일치합니다.");
                return false; // 비밀번호 불일치
            }

            // 회원 코드 변경
            cust.setCStatCd("D");
            custRepository.save(cust); //  업데이트

            // 이력 기록
            CustHist custHist = new CustHist();
            custHist.setCId(cust.getCId());
            custHist.setCCngCd("STAT");
            custHist.setCBf(oldCust.getCStatCd());
            custHist.setCAf(cust.getCStatCd());

            custHistRepository.save(custHist); // 이력 저장

            return true;
        } else {
            System.out.println("고객 정보를 찾을 수 없습니다");
            return false;
        }
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
    public CustDto toPwdDto2(Cust cust) {
        return CustDto.builder()
                .cStatCd(cust.getCStatCd())
                .cPwd(cust.getCPwd())
                .build(); // 빌더를 사용해 객체 생성
    }


    @Override
    public Cust toPwdEntity(Cust cust, CustDto custDto) {
    cust.setCPwd(pwdEncrypt(custDto.getCPwd())); // custDto의 비밀번호를 암호화하여 cust 객체에 설정
    return cust;
}

}