package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.securityutils.EncryptionUtil;
import com.example.greenspringboot.cust.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController
@Controller
//@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private CustService custService;

    @GetMapping("/login")
    public String login(@CookieValue(value = "cEmailCookie", defaultValue = "") String cEmail, Model model) {
        model.addAttribute("cEmailCookie", cEmail);
        return "loginForm";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String cEmail, String cPwd, String rememberEmail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes msg) {
        if (!custService.login(cEmail, cPwd, request)) {
            /*RedirectAttributes의 속성 addFlashAttribute를 통해 로그인 실패시 출력할 수 있는 변수와 공간을 저장*/
            msg.addFlashAttribute("loginFail", "msg");
            return "redirect:/login";
        }

        /*성공한 경우 세션에서 이전 URL을 가져옴*/
        HttpSession session = request.getSession();
        String toURL = (String) session.getAttribute("toURL");

        /*이전 URL이 있으면 해당 페이지로 리다이렉트, 없으면 인덱스*/
        toURL = (toURL != null && !toURL.isEmpty()) ? toURL : "/";

        /*로그인 후에는 이전 URL을 세션에서 삭제합니다.*/
        session.removeAttribute("toURL");

        /*로그인 세션 유효시간 1시간*/
        session.setMaxInactiveInterval(7200);

        if (rememberEmail != null) {
            try {
                // 이메일 암호화
                String encryptedEmail = EncryptionUtil.encrypt(cEmail);

                // 암호화된 이메일을 쿠키에 저장
                Cookie idcookie = new Cookie("cEmail", encryptedEmail);
                idcookie.setMaxAge(7 * 24 * 3600); // 7일 동안 유효
                idcookie.setSecure(true); // HTTPS에서만 전송
                idcookie.setHttpOnly(true); // JavaScript에서 접근 불가
                response.addCookie(idcookie);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Cookie idcookie = new Cookie("cEmail", "");
            idcookie.setMaxAge(0); // 쿠키 만료
            response.addCookie(idcookie);
        }


        return "redirect:" + toURL;
    }
}