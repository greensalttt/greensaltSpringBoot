package com.example.greenspringboot.payment.controller;

import com.example.greenspringboot.payment.service.PaymentService;
import com.example.greenspringboot.payment.service.PaymentServiceImpl;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PerformanceService performanceService;


//    @GetMapping("/page")
//    private String payment(@SessionAttribute("cId") Integer cId, Integer pno, Model m, HttpSession session){
////        if (ticketCount < 1 || ticketCount > 2) {
////            throw new IllegalArgumentException("티켓 수량은 1~2개 사이여야 합니다.");
////        }
////        session.setAttribute("ticketCount", ticketCount);
//
//        Integer ticketCount = (Integer) session.getAttribute("ticketCount");
//
//        int pricePerTicket = performanceService.getPriceByPno(pno);
//        int totalPrice = pricePerTicket * ticketCount;
//
//        m.addAttribute("ticketCount", ticketCount);
//        m.addAttribute("totalPrice", totalPrice);
//    return "payment";
//    }
}
