package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.cust.service.CustService;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class CustMyPageController {

    @Autowired
    private CustRepository custRepository;

//    @Autowired
    private final CustHistRepository custHistRepository;

    @Autowired
    private CustService custService;



    @GetMapping("/list")
    public String myPage(HttpSession session){
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPage";
    }

//    엔티티는 DB 전송, DTO는 데이터 전송
    @GetMapping("/info")
    public String myPageInfo(HttpSession session){
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPageInfo";
    }

    @PostMapping("/infoPost")
    public String myPageInfoPost(@ModelAttribute CustDto custDto, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        int cId = (int) session.getAttribute("cId");
        custDto.setCId(cId);
        custService.custHist(custDto);
        return "myPage";
    }

}
