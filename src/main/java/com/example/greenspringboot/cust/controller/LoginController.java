package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.cust.securityutils.EncryptionUtil;
import com.example.greenspringboot.cust.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController

//리퀘스트맵핑은 앞에 고정 url을 주고싶을떄 사용
//@RequestMapping

@Controller
public class LoginController {

    @Autowired
    private CustService custService;

    @Autowired
    private AdminService adminService;

//    쿠키에 있는 이메일이 암호화로 저장되기 때문에 다시 불러오기 위해 복호화 시켜 모델로 전달
    @GetMapping("/login")
    public String login(
            @CookieValue(value = "cEmail", defaultValue = "") String encryptedEmail,
            @RequestParam(value = "redirect", required = false) String redirect,
            HttpSession session,
            Model model
    ) {
        String decryptedEmail = "";
        try {
            if (!encryptedEmail.isEmpty()) {
                decryptedEmail = EncryptionUtil.decrypt(encryptedEmail); // 이메일 복호화
                System.out.println("복호화된 이메일: " + decryptedEmail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // redirect 파라미터가 있으면 세션에 저장
        if (redirect != null && !redirect.isEmpty()) {
            session.setAttribute("toURL", redirect);
            System.out.println("redirect 파라미터 세션에 저장: " + redirect);
        }

        model.addAttribute("decryptedEmail", decryptedEmail); // 복호화된 이메일을 모델에 추가
        return "loginForm"; // 로그인 폼 JSP 페이지로 반환
    }




    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String cEmail, String cPwd, String rememberEmail, HttpServletRequest request, HttpServletResponse response, RedirectAttributes msg, Model model) {
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
                System.out.println("암호화전 이메일: " + cEmail);

                // 이메일 암호화
                String encryptedEmail = EncryptionUtil.encrypt(cEmail);

                System.out.println("암호화 이메일: " + encryptedEmail);

                // 암호화된 이메일을 쿠키에 저장
                Cookie idcookie = new Cookie("cEmail", encryptedEmail);
                idcookie.setMaxAge(7 * 24 * 3600); // 7일 동안 유효
//                idcookie.setSecure(true); // HTTPS에서만 전송
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

    @PostMapping("/adminlogin")
    public String login(String aLoginId, String aPwd, HttpServletRequest request,  RedirectAttributes msg, Model model) {
        if (!adminService.adminLogin(aLoginId, aPwd, request, model)) {
            msg.addFlashAttribute("adminLoginFail", "msg");
            return "redirect:/login";
        }
        return "adminPage";
    }
}