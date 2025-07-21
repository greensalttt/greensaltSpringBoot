package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.entity.Order;
import com.example.greenspringboot.performance.dto.PerformanceDto;

public interface OrderService {
//    void orderPage(Integer pno, Model m);

    PerformanceDto orderPage(Integer pno);

    OrderDto orderConfirm(OrderDto orderDto);

//    Order saveOrder(OrderDto orderDto, String orderId, Integer userId);

    Order saveOrder(OrderDto orderDto, Integer cId);

//    PerformanceDto orderPage(Integer pno);
}
