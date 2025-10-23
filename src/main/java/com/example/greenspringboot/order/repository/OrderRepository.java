package com.example.greenspringboot.order.repository;

import com.example.greenspringboot.order.dto.MyReservationDto;
import com.example.greenspringboot.order.dto.PendingOrderDto;
import com.example.greenspringboot.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCreatedByOrderByOnoDesc(Integer cId);

    Optional<Order> findByOrderId(String orderId);

    // 지정된 상태의 주문 중, 생성 시간이 기준 시간보다 이전이면 주문 상태 무효
    @Modifying
    @Query("UPDATE Order o SET o.status = 'expired' WHERE o.status = :status AND o.createdAt < :time")
    void expirePendingOrders(@Param("status") String status, @Param("time") LocalDateTime time);


    @Query("SELECT new com.example.greenspringboot.order.dto.MyReservationDto(" +
            "o.ono, o.orderId, p.pno, p.title, p.artist, p.venue, p.date, o.ordererName, o.ticketCount, o.totalPrice, pay.paymentMethod, pay.createdAt, o.status) " +
            "FROM Order o " +
            "LEFT JOIN Payment pay ON o.ono = pay.ono " +
            "LEFT JOIN o.performance p " +
            "WHERE o.status = 'paid' AND o.createdBy = :cId")
    List<MyReservationDto> findMyReservations(@Param("cId") Integer cId);


}
