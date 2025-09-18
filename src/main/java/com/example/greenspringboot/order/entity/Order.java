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
//    오토이크리먼트 어노테이션
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


    // 1:1 관계는 주인 불분명해서 경로 기반 조인 불가, 직접 조인 필요 (1주문에는 1결제)
    // 지연로딩
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ono", insertable = false, updatable = false)
    private Payment payment;

    // 다대일 관계는 현재 엔티티가 주인이라 경로 기반 조인 가능 (1공연에는 다수 주문 가능)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno", insertable = false, updatable = false)
    private Performance performance;

}