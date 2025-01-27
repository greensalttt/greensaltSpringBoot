package com.example.greenspringboot.board.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "board_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class BoardHist {
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "b_hist_num", nullable = false)
    private Integer bHistNum;

    private Integer bno;

    @Column(name = "c_id", nullable = false)
    private Integer cId;

    @Column(name = "b_cng_cd", nullable = false)
    private String bCngCd;

    @Column(name = "b_bf", nullable = false)
    private String bBf;

    @Column(name = "b_af", nullable = false)
    private String bAf;

    @Builder.Default
    private Date frst_reg_dt = new Date();

    @Builder.Default
    private String frst_reg_id = "minwook";

    @Builder.Default
    private Date last_mod_dt = new Date();

    @Builder.Default
    private String last_mod_id = "minwook";
}