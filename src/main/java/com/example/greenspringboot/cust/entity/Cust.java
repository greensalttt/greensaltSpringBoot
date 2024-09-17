package com.example.greenspringboot.cust.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cust")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//엔티티에서 클라이언트 유효성 검사 대체 가능?
public class Cust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer c_id;

    @Builder.Default
    private String c_grd_cd = "Bronze";

    @Builder.Default
    private String c_stat_cd = "M";

    @Column(name = "c_email", nullable = false, unique = true)
    private String cEmail;

    @Column(nullable = false)
    private String c_pwd;

    @Column(nullable = false)
    private String c_name;

    @Column(nullable = false)
    private String c_nm;

    @Column(nullable = false)
    private String c_birth;

    @Column(nullable = false)
    private char c_gnd;

    @Column(nullable = false)
    private String c_phn;

    @Column(nullable = false)
    private String c_zip;

    @Column(nullable = false)
    private String c_road_a;

    @Column(nullable = false)
    private String c_jibun_a;

    @Column(nullable = false)
    private String c_det_a;

    @Builder.Default
    private char sms_agr = 'N';
    @Builder.Default
    private char email_agr = 'N';

    @Builder.Default
    private LocalDateTime reg_dt = LocalDateTime.now();

    @Builder.Default
    private LocalDateTime login_dt = LocalDateTime.now();

    @Builder.Default
    private int visit_cnt = 0;

    @Builder.Default
    private int tot_amt = 0;

    @Builder.Default
    private LocalDateTime frst_reg_dt = LocalDateTime.now();

    @Builder.Default
    private String frst_reg_id = "minwook";

    @Builder.Default
    private LocalDateTime last_mod_dt = LocalDateTime.now();

    @Builder.Default
    private String last_mod_id = "minwook";
}

