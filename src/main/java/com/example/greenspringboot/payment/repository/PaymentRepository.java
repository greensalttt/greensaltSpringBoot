package com.example.greenspringboot.payment.repository;
import com.example.greenspringboot.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Optional<Payment> findByOno(Integer ono);
}
