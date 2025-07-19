package com.example.greenspringboot.order.controller;

import com.example.greenspringboot.order.service.OrderService;
import com.example.greenspringboot.payment.service.PaymentService;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
//@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private PaymentService paymentService;


    @GetMapping("/order")
    private String order(@SessionAttribute("cId") Integer cId, Integer pno, Model m){
        orderService.orderPage(pno, m);
        return "order";
    }
//
    @PostMapping("/payment")
    private String orderPost(@SessionAttribute("cId") Integer cId, @RequestParam("ticketCount") Integer ticketCount, Integer pno, Model m, HttpSession session){

        if (ticketCount < 1 || ticketCount > 2) {
            throw new IllegalArgumentException("티켓 수량은 1~2개 사이여야 합니다.");
        }
        session.setAttribute("ticketCount", ticketCount);
        int pricePerTicket = performanceService.getPriceByPno(pno);
        int totalPrice = pricePerTicket * ticketCount;
        m.addAttribute("ticketCount", ticketCount);
        m.addAttribute("totalPrice", totalPrice);
        return "payment";
    }



}
