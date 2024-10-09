package com.example.greenspringboot.cust.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private Integer cHistNum;

    @Column(name = "c_id", nullable = false)
    private Integer cId;

    @Column(name = "c_cng_cd", nullable = false)
    private Integer cCngCd;

    @Column(name = "c_bf", nullable = false)
    private Integer cBf;

    @Column(name = "c_af", nullable = false)
    private Integer cAf;

    @Builder.Default
    private LocalDateTime frst_reg_dt = LocalDateTime.now();

    @Builder.Default
    private String frst_reg_id = "minwook";

    @Builder.Default
    private LocalDateTime last_mod_dt = LocalDateTime.now();

    @Builder.Default
    private String last_mod_id = "minwook";

}
