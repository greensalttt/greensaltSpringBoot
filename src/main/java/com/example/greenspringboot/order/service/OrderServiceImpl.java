package com.example.greenspringboot.order.service;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.dto.OrderDto;
import com.example.greenspringboot.order.dto.PendingOrderDto;
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


    // 현재 시간 기준 10분 이상 지난 '미결제' 주문 삭제
    @Transactional
    @Override
    public void deletePendingOrders() {
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(10);
        orderRepository.cleanUpOrders("pending", threshold);
    }


    @Override
    public List<MyReservationDto> getMyReservations(Integer cId) {
        return orderRepository.findMyReservations(cId);
    }

    @Override
    public List<PendingOrderDto> getPendingOrders(Integer cId) {
        return orderRepository.findPendingOrders(cId);
    }

}
