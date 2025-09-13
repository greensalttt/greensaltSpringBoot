package com.example.greenspringboot.config;
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


        if (session.getAttribute("cId") == null) {
            String query = request.getQueryString(); // 쿼리 스트링 얻기
            String fullURL = request.getRequestURI() + (query != null ? "?" + query : "");

            session.setAttribute("toURL", fullURL);
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
