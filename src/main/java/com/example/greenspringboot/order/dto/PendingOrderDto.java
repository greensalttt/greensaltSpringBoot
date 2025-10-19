package com.example.greenspringboot.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PendingOrderDto {
    private Integer ono;
    private String orderId;
    private Integer pno;
    private String title;
    private String artist;
    private String venue;
    private String date;
    private String ordererName;
    private Integer ticketCount;
    private Integer totalPrice;
    private String status;
}