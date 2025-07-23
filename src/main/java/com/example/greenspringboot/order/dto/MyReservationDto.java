package com.example.greenspringboot.order.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class MyReservationDto {
    private Integer ono;         // 예매 번호
    private String orderId;      // 주문 아이디 (필요하면)
    private Integer pno;         // 공연 번호
    private String title;        // 공연 제목
    private String artist;        // 공연 아티스트
    private String venue;        // 공연 장소
    private String date;        // 공연 날짜
    private String ordererName;  // 예매자
    private Integer ticketCount; // 티켓 수량
    private Integer totalPrice;  // 총 금액
    private String paymentMethod;// 결제 수단
    private Date createdAt;      // 결제일
    private String status;       // 주문 상태

}


