package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController
@Controller
@RequiredArgsConstructor
public class CustLoginController {

    private final CustService custService;
    @GetMapping("/login")
    public String login(@CookieValue(value = "cEmailCookie", defaultValue = "")String cEmail, Model model) {
        model.addAttribute("cEmailCookie", cEmail);
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }


    @PostMapping("/loginPost")
    public String loginPost(@RequestParam String cEmail, @RequestParam String cPwd, @RequestParam(required = false) String rememberEmail, HttpServletResponse response, HttpSession session, Model model) {

        CustDto custDto = custService.login(cEmail, cPwd);
        if (custDto != null) {
            /* session 변수에 고객 번호랑 닉네임 저장 */
            session.setAttribute("cId", custDto.getCId());
            session.setAttribute("cName", custDto.getCName());
            session.setAttribute("cNm", custDto.getCNm());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDate = custDto.getRegDt().format(formatter);
            session.setAttribute("regDt", formattedDate); // 포맷된 문자열을 세션에 저장

            String toURL = (String) session.getAttribute("toURL");

            /*이전 URL이 있으면 해당 페이지로 리다이렉트, 없으면 인덱스*/
            toURL = (toURL != null && !toURL.isEmpty()) ? toURL : "/";

            /*로그인 후에는 이전 URL을 세션에서 삭제합니다.*/
            session.removeAttribute("toURL");

            // 이메일 기억하기 처리
            if (rememberEmail != null) {
                Cookie idcookie = new Cookie("cEmailCookie", cEmail);
                idcookie.setMaxAge(7 * 24 * 3600); // 7일
                response.addCookie(idcookie);
            } else {
                Cookie idcookie = new Cookie("cEmailCookie", "");
                idcookie.setMaxAge(0); // 쿠키 삭제
                response.addCookie(idcookie);
            }

            System.out.println("Session cId: " + session.getAttribute("cId"));
            System.out.println("Session cName: " + session.getAttribute("cName"));
            System.out.println("Session cNm: " + session.getAttribute("cNm"));
            System.out.println("Session regDt: " + session.getAttribute("regDt"));

            return "redirect:" + toURL;
    } else {
        // 비밀번호가 틀렸을 경우 모델에 에러 메시지 추가
            model.addAttribute("cEmailCookie", cEmail);
        model.addAttribute("errorMessage", "이메일 또는 비밀번호가 잘못되었습니다.");
        return "loginForm"; // 로그인 페이지로 포워딩
    }
    }
}



