package com.example.greenspringboot.cust.entity;

//import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
//    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer c_id;

    @Builder.Default
    private String c_grd_cd = "Bronze";

    @Builder.Default
    private String c_stat_cd = "M";

    @Column(name = "c_email", nullable = false, unique = true)
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 주소여야 합니다.")
    private String cEmail;

    @Column(name = "c_pwd", nullable = false)
    @NotBlank(message = "비밀번호는 필수입니다.")
//    비밀번호 해쉬화는 따로 서비스를 만들어서 진행
    private String cPwd;

    @Column(nullable = false)
    @NotBlank(message = "이름은 필수입니다.")
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

