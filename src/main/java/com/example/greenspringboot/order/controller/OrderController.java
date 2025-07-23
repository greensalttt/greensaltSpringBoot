package com.example.greenspringboot.order.controller;

import com.example.greenspringboot.order.dto.OrderDto;
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
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    private String order(@SessionAttribute("cId") Integer cId, Integer pno, Model m){
        PerformanceDto performanceDto = orderService.orderPage(pno);
        m.addAttribute("performanceDto", performanceDto);
        return "order";
    }


    @PostMapping("/payment")
    public String paymentPage(@SessionAttribute("cId") Integer cId, @ModelAttribute OrderDto orderDto, Model m, Integer pno) {

        OrderDto orderConfirm = orderService.saveOrder(orderDto, cId);
        PerformanceDto performanceDto = orderService.orderPage(pno);

        m.addAttribute("orderDto", orderConfirm);
        m.addAttribute("performanceDto", performanceDto);

        return "payment";
    }



}
