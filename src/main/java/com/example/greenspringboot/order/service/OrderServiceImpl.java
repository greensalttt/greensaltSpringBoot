package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.entity.Order;
import com.example.greenspringboot.order.repository.OrderRepository;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public OrderDto saveOrder(OrderDto orderDto, Integer cId) {
        int price = performanceService.getPriceByPno(orderDto.getPno());
        int total = price * orderDto.getTicketCount();

        orderDto.setTotalPrice(total);

        String orderId = "order-" + System.currentTimeMillis();

        Order order = Order.builder()
                .pno(orderDto.getPno())
                .orderId(orderId)
                .ordererName(orderDto.getOrdererName())
                .ticketCount(orderDto.getTicketCount())
                .totalPrice(total)
                .status("pending")
                .createdAt(LocalDateTime.now())
                .createdBy(cId)
                .updatedAt(LocalDateTime.now())
                .updatedBy(cId)
                .build();

        Order savedOrder = orderRepository.save(order);

        // 저장 후 Entity → DTO 변환
        return OrderDto.builder()
                .pno(savedOrder.getPno())
                .orderId(savedOrder.getOrderId())
                .ordererName(savedOrder.getOrdererName())
                .ticketCount(savedOrder.getTicketCount())
                .totalPrice(savedOrder.getTotalPrice())
                .build();
    }


    // 현재 시간 기준 10분 이상 지난 '미결제' 주문 만료로 변경
    @Transactional
    @Override
    public void expirePendingOrders() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);
        orderRepository.expirePendingOrders("pending", threshold);
    }

    @Override
    public List<MyReservationDto> getMyReservations(Integer cId) {
        return orderRepository.findMyReservations(cId);
    }

//    내 모든 주문
    @Override
    public List<OrderDto> getMyOrders(Integer cId) {

        List<Order> orders = orderRepository.findByCreatedByOrderByOnoDesc(cId);

        return orders.stream()
                .map(order -> OrderDto.builder()
                        .ono(order.getOno())
                        .pno(order.getPerformance().getPno())
                        .orderId(order.getOrderId())
                        .ordererName(order.getOrdererName())
                        .ticketCount(order.getTicketCount())
                        .totalPrice(order.getTotalPrice())
                        .status(order.getStatus())
                        .createdAt(order.getCreatedAt())
                        .createdBy(order.getCreatedBy())
                        .updatedAt(order.getUpdatedAt())
                        .updatedBy(order.getUpdatedBy())
                        .build()
                )
                .collect(Collectors.toList());
    }

//    단일 주문
    @Override
    public OrderDto getOrderByOrderId(String orderId) {
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주문이 없습니다"));

        return OrderDto.builder()
                .ono(order.getOno())
                .pno(order.getPno())
                .orderId(order.getOrderId())
                .ordererName(order.getOrdererName())
                .ticketCount(order.getTicketCount())
                .totalPrice(order.getTotalPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .createdBy(order.getCreatedBy())
                .updatedAt(order.getUpdatedAt())
                .updatedBy(order.getUpdatedBy())
                .build();
    }

}
