package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.cust.dto.CustDto;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
    /*세션 속성에 저장되어있는 c_id를 가져와서 c_id 변수로 지정함*/
    public String myPage(HttpSession session, HttpServletRequest request) {
        Integer cId = (Integer) session.getAttribute("cId");

        if(cId != null) {

            custService.myPage(cId, request);
            return "myPage";
        }
        return "errorPageC";
    }

    //    엔티티는 DB 전송, DTO는 데이터 전송
    @GetMapping("/info")
    public String myPageInfo(HttpSession session) {
        System.out.println("개인정보변경 겟맵핑: " + session.getAttribute("cName"));
        return "myPageInfo";
    }

//    @PostMapping("/info")
//    public String myPageInfo(@ModelAttribute CustDto custDto, HttpServletRequest request, RedirectAttributes rattr) {
//
//        HttpSession session = request.getSession();
//
////        로그인할때 저장한 cId 세션을 변수로 저장
//        int cId = (int) session.getAttribute("cId");
//
//         Cust oldCust = custRepository.findById(cId);
//         CustDto oldData = custService.toDto(oldCust);
//
//            // 현재 데이터 설정
//            custDto.setCId(cId);
//
//            // 서비스 호출하여 정보 업데이트 및 이력 기록
//            custService.custModify(cId, custDto, oldData);
//            custService.updateSession(session, custDto);
//
//
//            rattr.addFlashAttribute("msg", "CMOD_OK");
//            return "redirect:/mypage/list";
//    }

    @PostMapping("/info")
    public String myPageInfo(@ModelAttribute CustDto custDto, HttpServletRequest request, RedirectAttributes rattr) {
        HttpSession session = request.getSession();

        // 로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        // 서비스 호출하여 정보 업데이트 및 이력 기록
        boolean updateResult = custService.updateCustInfo(cId, custDto, session);

        if (updateResult) {
            rattr.addFlashAttribute("msg", "CMOD_OK");
        } else {
            rattr.addFlashAttribute("msg", "CUSTOMER_NOT_FOUND");
        }
        return "redirect:/mypage/list";
    }


    @GetMapping("/pwdEdit")
    public String pwdEdit(HttpSession session){
        System.out.println("비밀번호 변경: "+ session.getAttribute("cName"));
        return "pwdEdit";
    }

//    @PostMapping("/pwdEdit")
//    public String pwdEdit(CustDto custDto, HttpServletRequest request, HttpSession sessionLogout, String curPwd, RedirectAttributes msg) {
//        HttpSession session = request.getSession();
//
////        로그인할때 저장한 cId 세션을 변수로 저장
//        int cId = (int) session.getAttribute("cId");
//
//            Cust cust = custRepository.findBycId(cId);
//            CustDto oldPwd = custService.toPwdDto(cust);
//            custDto.setCId(cId);
//
////            /* 입력한 현재 비밀번호와 실제 비밀번호가 일치하는지 확인*/
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            if (!encoder.matches(curPwd, oldPwd.getCPwd())) {
//                msg.addFlashAttribute("pwdFail", "pwdMsg");
//
//                return "redirect:/mypage/pwdEdit";
//            }
//
//            custService.pwdChange(cId, custDto, oldPwd);
//            System.out.println("비밀번호 변경 완료");
//            sessionLogout.invalidate();
//            return "redirect:/";
//        }

    @PostMapping("/pwdEdit")
    public String pwdEdit(CustDto custDto, HttpServletRequest request, HttpSession sessionLogout, String curPwd, RedirectAttributes msg) {
        HttpSession session = request.getSession();

        // 로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        // 비밀번호 변경을 위한 서비스 호출
        boolean passwordChanged = custService.pwdChange(cId, custDto, curPwd);

        if (!passwordChanged) {
            msg.addFlashAttribute("pwdFail", "pwdMsg");
            return "redirect:/mypage/pwdEdit"; // 실패 시, 비밀번호 수정 페이지로 리다이렉트
        }

        // 비밀번호 변경 후, 로그아웃 처리
        sessionLogout.invalidate();
        return "redirect:/"; // 성공 시, 로그아웃 후 홈페이지로 리다이렉트
    }

}



