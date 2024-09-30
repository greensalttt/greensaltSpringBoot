//package com.example.greenspringboot.cust.interceptor;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Component
//public class CustInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//            throws Exception {
////        request = 클라이언트의 http의 요청 정보를 담는다 (세션,a url)
//        HttpSession session = request.getSession();
//        if(session.getAttribute("cId") == null) {
//            response.sendRedirect("/login");
//            System.out.println("인터셉터 세션 확인" + session.getAttribute("cId"));
//            return false;
//        }
//        return true;
//    }
//}

package com.example.greenspringboot.cust.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class CustInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 현재 연결된 세션 객체를 새로운 세션에 저장
        HttpSession session = request.getSession();

        // 현재 요청 URL
        String requestURL = request.getRequestURI();

        System.out.println("현재 요청 URL: " + requestURL);
        System.out.println("인터셉터 세션 cId 확인: " + session.getAttribute("cId"));

        if (session.getAttribute("cId") == null) {
            session.setAttribute("toURL", request.getRequestURI());
            response.sendRedirect("/login");
            System.out.println("세션을 못찾아서 로그인 페이지로 리다이렉트");
            return false; // 세션이 없으면 요청을 중단
        }

        return true; // 세션이 있으면 다음으로 진행
    }
}
