package com.example.greenspringboot.cust.interceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CustInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 현재 연결된 세션 객체를 새로운 세션에 저장
        HttpSession session = request.getSession();

        // 현재 요청 URL
        String requestURL = request.getRequestURI();

        System.out.println("인터셉터 요청 URL: " + requestURL);
        System.out.println("인터셉터 세션 cId 확인: " + session.getAttribute("cId"));

//        세션 객체에 cId 속성이 있는지 없는지 확인
        if (session.getAttribute("cId") == null) {
            session.setAttribute("toURL", request.getRequestURI());
            response.sendRedirect("/login");
            System.out.println("세션을 못찾아서 로그인 페이지로 리다이렉트");
            return false;
        }

        return true;
    }
}
