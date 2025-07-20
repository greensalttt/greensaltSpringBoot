package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import org.springframework.ui.Model;

public interface OrderService {
//    void orderPage(Integer pno, Model m);

    PerformanceDto orderPage(Integer pno);

    OrderDto orderConfirm(OrderDto orderDto);

//    PerformanceDto orderPage(Integer pno);
}
