package com.example.greenspringboot.cust.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CustInterceptor custInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(custInterceptor)
//                .addPathPatterns("/**") // 인터셉터를 적용할 URL 패턴
//                .excludePathPatterns("/", "/login", "/register", "/loginPost", "/css/**"); // 인터셉터에서 제외할 URL 패턴
                .addPathPatterns("/myPage");
    }
}
