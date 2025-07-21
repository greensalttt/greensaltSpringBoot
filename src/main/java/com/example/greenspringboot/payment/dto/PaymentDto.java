package com.example.greenspringboot.payment.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PaymentDto {
    private Integer paymentNo;
    private Integer ono;
    private String paymentKey;
    private String paymentMethod;
    private Integer amount;
    private String paymentStatus;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
}
