package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController
@Controller
@RequiredArgsConstructor
public class CustLoginController {

    private final CustService custService;
    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }


    @PostMapping("/loginPost")
    public String loginPost(@RequestParam String cEmail, @RequestParam String cPwd, HttpServletRequest request) {
        CustDto custDto = custService.login(cEmail, cPwd);
        if (custDto != null) {
            /* 새로운 세션 생성 */
            HttpSession session = request.getSession();
            /* session 변수에 고객 번호랑 닉네임 저장 */
            session.setAttribute("c_id", custDto.getCId());
            session.setAttribute("c_nm", custDto.getCNm());

            System.out.println("Session c_id: " + session.getAttribute("c_id"));
            System.out.println("Session c_nm: " + session.getAttribute("c_nm"));

            return "index";
        }
        return "errorPage";
    }




}



