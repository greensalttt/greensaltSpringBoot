package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {

    @Autowired
    private CustRepository custRepository;

//    서비스에서 만든 메서드를 사용할려면 오토와이어드 어노테이션 필수
    @Autowired
    private CustService custService;


    @GetMapping("/list")
//    view 이름을 반환해야하니 메서드의 타입을 String
    public String myPage(HttpSession session) {
        System.out.println("마이페이지 겟맵핑: " + session.getAttribute("cName"));
        return "myPage";
    }

    //    엔티티는 DB 전송, DTO는 데이터 전송
    @GetMapping("/info")
    public String myPageInfo(HttpSession session) {
        System.out.println("개인정보변경 겟맵핑: " + session.getAttribute("cName"));
        return "myPageInfo";
    }

    @PostMapping("/infoPost")
    public String myPageInfoPost(@ModelAttribute CustDto custDto, HttpServletRequest request) {

        HttpSession session = request.getSession();

//        로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        Optional<Cust> optionalCust = custRepository.findById(cId);
        if (optionalCust.isPresent()) {
            Cust oldCust = optionalCust.get();
            CustDto oldData = custService.toDto(oldCust);

            // 현재 데이터 설정
            custDto.setCId(cId);

            // 서비스 호출하여 정보 업데이트 및 이력 기록
            custService.custHist(custDto, oldData);

            custService.updateSession(session, custDto);
            System.out.println("개인정보 변경 완료");


            return "redirect:/mypage/list";
        } else {
            // cId에 해당하는 고객 정보가 없을 경우 처리
            return "errorPage";
        }
    }

    @GetMapping("/pwdEdit")
    public String pwdEdit(){
        return "pwdEdit";
    }

    @PostMapping("/pwdEditPost")
    public String pwdEditPost(CustDto custDto, HttpServletRequest request, HttpSession sessionLogout, String curPwd, RedirectAttributes msg) {
        HttpSession session = request.getSession();

//        로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

//        cust 엔티티를 들고오는 코드이므로 메서드 만들시 매개변수로 엔티티를 받아야됨
        Optional<Cust> optionalCust = custRepository.findById(cId);
        if (optionalCust.isPresent()) {
            Cust oldCust = optionalCust.get();
            CustDto oldData = custService.toPwdDto(oldCust);

            custDto.setCId(cId);

//            /* 입력한 현재 비밀번호와 실제 비밀번호가 일치하는지 확인*/
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(curPwd, oldData.getCPwd())) {
                msg.addFlashAttribute("pwdFail", "pwdMsg");


                return "redirect:/mypage/pwdEdit";
            }

            custService.pwdChange(custDto, oldData);
            System.out.println("비밀번호 변경 완료");
            sessionLogout.invalidate();
            return "redirect:/";
        } else {
            // cId에 해당하는 고객 정보가 없을 경우 처리
            return "errorPage";
        }
    }}


