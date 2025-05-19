package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.cust.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


//    서비스에서 만든 메서드를 사용할려면 오토와이어드 어노테이션 필수
    @Autowired
    private CustService custService;

    @GetMapping("/list")
    public String myPage(HttpSession session, Model model) {
        Integer cId = (Integer) session.getAttribute("cId");
        if(cId != null) {
            custService.myPage(cId, model);
            return "myPage";
        }
        return "errorPageC";
    }

    //    엔티티는 DB 값이 변경될때, DTO는 눈에 보이는 데이터 화면으로 전송할때
    @GetMapping("/info")
    public String myPageInfo(HttpSession session, Model model) {
        Integer cId = (Integer) session.getAttribute("cId");
        if(cId != null) {
            custService.myPageInfo(cId, model);
            return "myPageInfo";
        }
        return "errorPageC";
    }

    @PostMapping("/info")
    public String myPageInfo(HttpSession session, @ModelAttribute CustDto custDto, RedirectAttributes rattr) {

        Integer cId = (Integer) session.getAttribute("cId");
        boolean updateResult = custService.custModify(cId, custDto);

        if (updateResult) {
            rattr.addFlashAttribute("msg", "CMOD_OK");
        } else {
            rattr.addFlashAttribute("msg", "CUSTOMER_NOT_FOUND");
        }
        return "redirect:/mypage/list";
    }


    @GetMapping("/editPwd")
    public String editPwd(HttpSession session, Model model) {
        Integer cId = (Integer) session.getAttribute("cId");
        if(cId != null) {
            custService.myPage(cId, model);
            return "editPwd";
        }
        return "errorPageC";
    }

    @PostMapping("/editPwd")
    public String editPwd(CustDto custDto, HttpServletRequest request, HttpSession sessionLogout, String curPwd, RedirectAttributes msg) {
        HttpSession session = request.getSession();

        // 로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        // 비밀번호 변경을 위한 서비스 호출
        boolean passwordChanged = custService.pwdChange(cId, custDto, curPwd);

        if (!passwordChanged) {
            msg.addFlashAttribute("pwdFail", "pwdMsg");
            return "redirect:/mypage/editPwd"; // 실패 시, 비밀번호 수정 페이지로 리다이렉트
        }

        // 비밀번호 변경 후, 로그아웃 처리
        sessionLogout.invalidate();
        msg.addFlashAttribute("pwdClear", "pwdMsg");

        return "redirect:/"; // 성공 시, 로그아웃 후 홈페이지로 리다이렉트
    }


    @PostMapping("/drop")
    public String custDrop(HttpServletRequest request, HttpSession sessionLogout, String dropPwd, RedirectAttributes msg) {
        HttpSession session = request.getSession();

        // 로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        // 비밀번호 변경을 위한 서비스 호출
        boolean passwordChanged = custService.custDrop(cId,dropPwd);
        System.out.println("입력받은 dropPwd: " + dropPwd);

        if (!passwordChanged) {
            // 메시지가 안뜸
            msg.addFlashAttribute("pwdFail", "pwdMsg");
            return "redirect:/mypage/info"; // 실패 시, 비밀번호 수정 페이지로 리다이렉트;
        }
        // 비밀번호 변경 후, 로그아웃 처리
        sessionLogout.invalidate();
        msg.addFlashAttribute("dropClear", "dropMsg");

        return "redirect:/"; // 성공 시, 로그아웃 후 홈페이지로 리다이렉트
    }

}



