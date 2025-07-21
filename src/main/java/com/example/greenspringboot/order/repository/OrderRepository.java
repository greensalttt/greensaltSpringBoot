package com.example.greenspringboot.order.repository;

import com.example.greenspringboot.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
