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
    public String myPage(HttpSession session) {
        System.out.println("마이페이지 연결: " + session.getAttribute("cName"));
        return "myPage";
    }

    //    엔티티는 DB 전송, DTO는 데이터 전송
    @GetMapping("/info")
    public String myPageInfo(HttpSession session) {
        System.out.println("개인장보변경 연결: " + session.getAttribute("cName"));
        return "myPageInfo";
    }

    @PostMapping("/infoPost")
    public String myPageInfoPost(@ModelAttribute CustDto custDto, HttpServletRequest request) {

        HttpSession session = request.getSession();

//        로그인할때 저장한 cId 세션을 변수로 저장
        int cId = (int) session.getAttribute("cId");

        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            // Optional에서 엔티티 꺼내기
            Cust oldCust = optionalCust.get();

            // Cust 엔티티를 CustDto로 변환
            CustDto oldData = new CustDto();
            oldData.setCId(oldCust.getCId());
            oldData.setCZip(oldCust.getCZip());
            oldData.setCRoadA(oldCust.getCRoadA());
            oldData.setCJibunA(oldCust.getCJibunA());
            oldData.setCDetA(oldCust.getCDetA());
            oldData.setCPhn(oldCust.getCPhn());
            oldData.setCBirth(oldCust.getCBirth());
            oldData.setSmsAgr(oldCust.getSmsAgr());
            oldData.setEmailAgr(oldCust.getEmailAgr());


            // 현재 데이터 설정
            custDto.setCId(cId);

            // 서비스 호출하여 정보 업데이트 및 이력 기록
            custService.custHist(custDto, oldData);

//            updateSession(session, custDto);
            custService.updateSession(session, custDto);


            return "redirect:/mypage/list";
        } else {
            // cId에 해당하는 고객 정보가 없을 경우 처리
            return "errorPage";
        }
    }
}
