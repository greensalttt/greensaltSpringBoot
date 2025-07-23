package com.example.greenspringboot.payment.service;
import com.example.greenspringboot.order.entity.Order;
import com.example.greenspringboot.order.repository.OrderRepository;
import com.example.greenspringboot.order.service.OrderService;
import com.example.greenspringboot.payment.entity.Payment;
import com.example.greenspringboot.payment.repository.PaymentRepository;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment savePayment(String orderId, String paymentKey, String paymentMethod, Integer amount, Integer cId) {

        Order order =orderRepository.findByOrderId(orderId)
                .orElseThrow(()-> new IllegalArgumentException("주문 데이터 없음"));

        order.setStatus("paid");
        order.setUpdatedAt(LocalDateTime.now());
        order.setUpdatedBy(cId);
        orderRepository.save(order);

        Integer ono = order.getOno();

        Payment payment = Payment.builder()
                .ono(ono)
                .paymentKey(paymentKey)
                .paymentMethod(paymentMethod)
                .amount(amount)
                .paymentStatus("paid")
                .createdAt(new Date())
                .createdBy(cId)
                .updatedAt(LocalDateTime.now())
                .updatedBy(cId)
                .build();

        return paymentRepository.save(payment);
    }
}
