package com.example.greenspringboot.config;
import com.example.greenspringboot.order.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;

@Component
public class OrderCleanupScheduler {

    @Autowired
    private OrderService orderService;


    @Scheduled(fixedRate = 600000) // 10분마다
    public void expireOldOrders() {
        orderService.expirePendingOrders();
        System.out.println("10분 경과된 미결제 주문 'expired' 처리: " + LocalDateTime.now());
    }

}
