package com.example.greenspringboot.cust.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cust_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CustHist {
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_hist_num", nullable = false)
    private int cHistNum;

    @Column(name = "c_cng_cd", nullable = false)
    private String cCngCd;

    @Column(name = "c_bf", nullable = false)
    private String cBf;

    @Column(name = "c_af", nullable = false)
    private String cAf;

    @Builder.Default
    @Column(name= "created_at")
    private Date createdAt = new Date();

    @Builder.Default
    @Column(name= "created_by")
    private String createdBy = "user";

}
