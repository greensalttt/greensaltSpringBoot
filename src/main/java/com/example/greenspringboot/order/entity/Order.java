package com.example.greenspringboot.order.entity;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "order")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ono")
    private Integer ono;  // 예매 번호 (PK)

    @Column(name = "pno", nullable = false)
    private Integer pno;  // 공연 번호 (FK)

    @Column(name = "ticket_count", nullable = false)
    @Builder.Default
    private Integer ticketCount = 1;  // 예매 티켓 수량 (기본값 1)

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;  // 총 가격

    @Column(name = "status", length = 10)
    @Builder.Default
    private String status = "reserved";  // 예매 상태 ('reserved', 'cancelled' 등)

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();  // 생성 시간

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;  // 예매한 유저 ID

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();  // 마지막 수정 시간

    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;  // 수정한 유저 ID
}