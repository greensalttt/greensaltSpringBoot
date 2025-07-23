package com.example.greenspringboot.order.entity;
import com.example.greenspringboot.payment.entity.Payment;
import com.example.greenspringboot.performance.entity.Performance;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "`order`")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ono")
    private Integer ono;

    @Column(name = "pno", nullable = false)
    private Integer pno;

    @Column(name ="order_id", nullable = false)
    private String orderId;

    @Column(name = "orderer_name", nullable = false)
    private String ordererName;

    @Column(name = "ticket_count", nullable = false)
    @Builder.Default
    private Integer ticketCount = 1;  // 예매 티켓 수량 (기본값 1)

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;  // 총 가격

    @Column(name = "status", nullable = false)
    @Builder.Default
    private String status = "pending";

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "created_by", nullable = false)
    private Integer createdBy;

    @Column(name = "updated_at", nullable = false)
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "updated_by", nullable = false)
    private Integer updatedBy;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono", insertable = false, updatable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno", insertable = false, updatable = false)
    private Performance performance;


}