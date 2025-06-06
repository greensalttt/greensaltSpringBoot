package com.example.greenspringboot.performance.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "performance_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PerformanceHist{

    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_hist_num", nullable = false)
    private Integer pHistNum;

    private Integer pno;

    @Column(name = "p_cng_cd", nullable = false)
    private String pCngCd;

    @Column(name = "p_bf", nullable = false)
    private String pBf;

    @Column(name = "p_af", nullable = false)
    private String pAf;

    @Builder.Default
    @Column(name= "created_at")
    private Date createdAt = new Date();

//    @Builder.Default
    @Column(name= "created_by")
    private Integer createdBy;
}