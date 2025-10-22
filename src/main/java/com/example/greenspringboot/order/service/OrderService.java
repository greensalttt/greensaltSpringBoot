package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.dto.PendingOrderDto;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    PerformanceDto orderPage(Integer pno);

    OrderDto saveOrder(OrderDto orderDto, Integer cId);

    @Transactional
    void expirePendingOrders();

    List<MyReservationDto> getMyReservations(Integer cId);

}
