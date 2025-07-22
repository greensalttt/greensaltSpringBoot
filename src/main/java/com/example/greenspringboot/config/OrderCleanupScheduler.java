package com.example.greenspringboot.config;
import com.example.greenspringboot.order.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.greenspringboot.order.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class OrderCleanupScheduler {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;


//    @Transactional
//    @Scheduled(fixedRate = 60000) // 1분마다 실행
//    public void deleteExpiredOrders() {
//        LocalDateTime threshold = LocalDateTime.now().minusMinutes(1);
//
//        orderRepository.cleanUpOrders("pending", threshold);
//
//        System.out.println("[스케줄러] 1분 경과된 미결제 주문 삭제: " + LocalDateTime.now());
//    }

    @Scheduled(fixedRate = 600000) // 10분마다 실행
    public void deleteOrders() {
        orderService.deleteOrders();
        System.out.println("[스케줄러] 10분 경과된 미결제 주문 삭제: " + LocalDateTime.now());
    }
}
