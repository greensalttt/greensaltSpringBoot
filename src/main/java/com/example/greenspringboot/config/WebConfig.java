package com.example.greenspringboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CustInterceptor custInterceptor;

    @Autowired
    private AdminInterceptor adminInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(custInterceptor)
                .addPathPatterns("/mypage/**","/board/write", "/order/**", "/payment/**");

        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/admin/**");

    }



//      로컬용 3개 수정해야댐 앨범 공연 서비스 + 앱파스
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/images/**")
//                .addResourceLocations("file:///C:/album/", "file:///C:/performance/");
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/home/ubuntu/album/", "file:/home/ubuntu/performance/");
    }



}
