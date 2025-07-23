package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
//    void orderPage(Integer pno, Model m);

    PerformanceDto orderPage(Integer pno);

//    OrderDto orderConfirm(OrderDto orderDto);

//    Order saveOrder(OrderDto orderDto, String orderId, Integer userId);

    OrderDto saveOrder(OrderDto orderDto, Integer cId);

    @Transactional
    void deleteOrders();

    List<MyReservationDto> findMyReservations(Integer cId);

//    Order findByOrderId(String orderId);

//    void savePayment(String , String , String , Long , Integer );

//    PerformanceDto orderPage(Integer pno);
}
