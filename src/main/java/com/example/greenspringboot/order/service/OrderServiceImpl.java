package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.entity.Order;
import com.example.greenspringboot.order.repository.OrderRepository;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PerformanceService performanceService;

    @Override
    public PerformanceDto orderPage(Integer pno){
        Performance performance =  performanceRepository.findById(pno)
                .orElseThrow(()-> new IllegalArgumentException("공연 데이터 없음"));

            return PerformanceDto.builder()
                .pno(performance.getPno())
                .title(performance.getTitle())
                .price(performance.getPrice())
                .build();

    }
    @Override
    public OrderDto orderConfirm(OrderDto orderDto) {
        int price = performanceService.getPriceByPno(orderDto.getPno());
        int total = price * orderDto.getTicketCount();

        orderDto.setTotalPrice(total);
        return orderDto;
    }

//    @Override
//    public void saveOrder(OrderDto orderDto, String orderId, Integer cId) {
//        Order order = Order.builder()
//                .orderId(orderId)
//                .pno(orderDto.getPno())
//                .orderName(orderDto.getOrderName())
//                .ticketCount(orderDto.getTicketCount())
//                .totalPrice(orderDto.getTotalPrice())
//                .createdBy(cId)
//                .updatedBy(cId)
//                .build();
//
//        orderRepository.save(order);
//    }

    @Override
    public Order saveOrder(OrderDto orderDto, Integer cId) {

        String orderId = "order-" + System.currentTimeMillis();

        Order order = Order.builder()
                .pno(orderDto.getPno())
                .orderId(orderId)
                .orderName(orderDto.getOrderName())
                .ticketCount(orderDto.getTicketCount())
                .totalPrice(orderDto.getTotalPrice())
                .status("pending")  // 기본 결제 대기 상태
                .createdAt(LocalDateTime.now())
                .createdBy(cId)
                .updatedAt(LocalDateTime.now())
                .updatedBy(cId)
                .build();

        return orderRepository.save(order);
    }


}
