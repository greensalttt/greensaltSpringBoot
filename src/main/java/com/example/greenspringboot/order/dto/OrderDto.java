package com.example.greenspringboot.order.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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


    public Date getCreatedAtAsDate() {
        if (createdAt == null) return null;
        return Date.from(createdAt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Date getUpdatedAtAsDate() {
        if (updatedAt == null) return null;
        return Date.from(updatedAt.atZone(ZoneId.systemDefault()).toInstant());
    }


}

