package com.example.greenspringboot.cust.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "cust")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

//엔티티에서 클라이언트 유효성 검사 대체 가능?
public class Cust {

//    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id", nullable = false)
    private Integer cId;

    @Builder.Default
    @Column(name = "c_stat_cd", nullable = false)
    private String statCd = "M";


    @Column(name = "c_email", nullable = false, unique = true)
    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "유효한 이메일 주소여야 합니다.")
    private String cEmail;

    @Column(name = "c_pwd", nullable = false)
    @NotBlank(message = "비밀번호는 필수입니다.")
//    비밀번호 해쉬화는 따로 서비스를 만들어서 진행
    private String cPwd;

    @Column(name = "c_nick", nullable = false)
    @NotBlank(message = "닉네임은 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z1-9]{2,10}$", message = "닉네임은 한글,영어, 숫자 조합으로 2~10자 이하로 입력해야 합니다.")
    private String cNick;

    @Column(name= "c_zip", nullable = false)
    @NotBlank(message = "우편번호는 필수입니다.")
    @Pattern(regexp = "^[0-9]{1,6}$", message = "우편번호는 6자리 이하의 숫자여야 합니다.")
    private String cZip;

    @Column(name="c_road_a", nullable = false)
    @NotBlank(message = "도로명 주소는 필수입니다.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "도로명 주소는 특수문자(공백, -, /)만 포함 가능하며 30자 이하여야 합니다.")
    private String cRoadA;

    @Column(name="c_jibun_a")
    @Size(max = 30, message = "지번 주소는 30자 이하여야 합니다.")
    private String cJibunA;

    @Column(name = "c_det_a", nullable = false)
    @NotBlank(message = "상세 주소는 필수입니다.")
    @Size(min = 1, max = 30, message = "상세 주소는 1자 이상 30자 이하로 입력해주세요.")
    private String cDetA;


    @Builder.Default
    @Column(name= "login_dt")
    private Date loginDt = new Date();

    @Builder.Default
    @Column(name= "visit_cnt")
    private Long visitCnt = 0L;

    @Builder.Default
    @Column(name= "created_at")
    private Date createdAt = new Date();

//    @Column(name= "created_by")
//    private Integer createdBy;

    @Builder.Default
    @Column(name= "updated_at")
    private Date updatedAt = new Date();

//    @Column(name= "updated_by")
//    private Integer updatedBy;

}


