package com.example.greenspringboot.payment.service;


import com.example.greenspringboot.payment.entity.Payment;
import org.springframework.ui.Model;

public interface PaymentService {
//    Payment savePayment(Integer ono, String paymentKey, String paymentMethod, Integer amount, Integer userId);

    Payment savePayment(String orderId, String paymentKey, String paymentMethod, Integer amount, Integer cId);



//    void paymentPage(Integer pno, Model m);
}
