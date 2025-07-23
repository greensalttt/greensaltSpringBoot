package com.example.greenspringboot.order.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private Integer ono;
    private Integer pno;
    private String orderId;
    private String ordererName;
    private Integer ticketCount;
    private Integer totalPrice;
    private String status;
    private LocalDateTime createdAt;
    private Integer createdBy;
    private LocalDateTime updatedAt;
    private Integer updatedBy;
}
