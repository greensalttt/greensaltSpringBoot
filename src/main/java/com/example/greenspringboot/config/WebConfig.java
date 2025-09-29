package com.example.greenspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CustInterceptor custInterceptor;

    //      로컬용 4개 수정해야댐 앨범, 공연 서비스 + 앱파스 디비, 웹콘피크
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("file:///C:/album/", "file:///C:/performance/");
//
//        registry.addResourceHandler("/admin/**")
//                .addResourceLocations("classpath:/static/admin/")
//                .resourceChain(true);
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/home/ubuntu/album/", "file:/home/ubuntu/performance/");

        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/static/admin/")
                .resourceChain(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(custInterceptor)
                .addPathPatterns("/mypage/**","/board/write", "/order/**", "/payment/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")              // API 경로에 대해
                .allowedOrigins("https://greensalt.site")  // 허용할 프론트엔드 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 허용할 HTTP 메서드
                .allowCredentials(true);             // 쿠키 전달 허용
    }

}