package com.example.greenspringboot.order.repository;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findByOrderId(String orderId);

    @Modifying
    @Query("DELETE FROM Order o WHERE o.status = :status AND o.createdAt < :time")
    void cleanUpOrders(@Param("status") String status, @Param("time") LocalDateTime time);



    @Query("SELECT new com.example.greenspringboot.order.dto.MyReservationDto(" +
            "o.ono, o.orderId, p.pno, p.title, p.artist, p.venue, p.date, o.ordererName, o.ticketCount, o.totalPrice, pay.paymentMethod, pay.createdAt, o.status) " +
            "FROM Order o " +
            "LEFT JOIN Payment pay ON o.ono = pay.ono " +
            "LEFT JOIN o.performance p " +
            "WHERE o.status = 'paid' AND o.createdBy = :cId")
    List<MyReservationDto> findMyReservations(@Param("cId") Integer cId);




}
