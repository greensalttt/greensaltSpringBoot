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
    @Column(name = "c_id", nullable = false)
    private Integer cId;

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

    @Column(name = "c_name", nullable = false)
    @NotBlank(message = "이름은 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z]{1,15}$", message = "이름은 한글과 영어만 입력 가능하며, 15자 이하로 입력해야 합니다.")
    private String cName;

    @Column(name = "c_nm", nullable = false)
    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z1-9]{2,10}$", message = "닉네임은 한글,영어, 숫자 조합으로 2~10자 이하로 입력해야 합니다.")
    private String cNick;

    @Column(name = "c_birth", nullable = false)
    @NotBlank(message = "생년월일은 필수입니다.")
    private String cBirth;

    @Column(name="c_gnd", nullable = false)
    private String cGnd;

    @Column(name = "c_phn", nullable = false)
    @NotBlank(message = "핸드폰 입력은 필수입니다.")
    @Pattern(regexp = "^[1-9]{11,12}$", message = "핸드폰 번호는 11~12자 사이여야 합니다.")
    private String cPhn;

    @Column(name= "c_zip", nullable = false)
    @NotBlank(message = "우편번호는 필수입니다.")
    @Pattern(regexp = "^[0-9]{1,6}$", message = "우편번호는 6자리의 숫자여야 합니다.")
    private String cZip;

    @Column(name="c_road_a", nullable = false)
    @NotBlank(message = "도로명 주소는 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "도로명 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
    private String cRoadA;

    @Column(name="c_jibun_a")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "지번 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
    private String cJibunA;

    @Column(name="c_det_a", nullable = false)
    @NotBlank(message = "상세 주소는 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "상세 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
    private String cDetA;

    @Column(name="sms_agr")
    @Builder.Default
    private char smsAgr = 'N';

    @Column(name="email_agr")
    @Builder.Default
    private char emailAgr = 'N';

    @Builder.Default
    @Column(name= "reg_dt", nullable = false)
    private LocalDateTime regDt = LocalDateTime.now();

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

