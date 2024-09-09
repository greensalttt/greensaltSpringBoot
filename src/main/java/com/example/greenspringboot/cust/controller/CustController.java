package com.example.greenspringboot.cust.controller;

import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

// 레스트컨트롤러는 서버간의 데이터를 통신할때만, 뷰를 반환할때는 일반 컨트롤러
//@RestController
@Controller
@RequiredArgsConstructor
public class CustController {

    private final CustService custService;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @PostMapping("/join")
    public String join(@RequestBody CustDto custDto) {
        String result = custService.join(custDto);

        if(result.equalsIgnoreCase("success")){
            return "success";
        }else{
            return "fail";
        }
    }
}


