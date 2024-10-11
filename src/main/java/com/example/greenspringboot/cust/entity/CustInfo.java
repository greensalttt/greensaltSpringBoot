//package com.example.greenspringboot.cust.entity;
//
//import lombok.*;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "cust")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CustInfo {
//    //    pk값 설정
//    @Id
////    오토 인크리먼트 설정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "c_id", nullable = false)
//    private Integer cId;
//
//    @Column(name = "c_phn", nullable = false)
//    @NotBlank(message = "핸드폰 입력은 필수입니다.")
//    @Pattern(regexp = "^[1-9]{11,12}$", message = "핸드폰 번호는 11~12자 사이여야 합니다.")
//    private String cPhn;
//
//    @Column(name= "c_zip", nullable = false)
//    @NotBlank(message = "우편번호는 필수입니다.")
//    @Pattern(regexp = "^[0-9]{1,6}$", message = "우편번호는 6자리의 숫자여야 합니다.")
//    private String cZip;
//
//    @Column(name="c_road_a", nullable = false)
//    @NotBlank(message = "도로명 주소는 필수입니다.")
//    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "도로명 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
//    private String cRoadA;
//
//    @Column(name="c_jibun_a")
//    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "지번 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
//    private String cJibunA;
//
//    @Column(name="c_det_a", nullable = false)
//    @NotBlank(message = "상세 주소는 필수입니다.")
//    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s\\-/]{1,30}$", message = "상세 주소는 특수문자(공백, -, /) 포함 30자 이하여야 합니다.")
//    private String cDetA;
//
//    @Column(name = "c_birth", nullable = false)
//    @NotBlank(message = "생년월일은 필수입니다.")
//    private String cBirth;
//
//    @Column(name="sms_agr")
//    @Builder.Default
//    private String smsAgr = "N";
//
//    @Column(name="email_agr")
//    @Builder.Default
//    private String emailAgr = "N";
//
//    @Builder.Default
//    @Column(name= "reg_dt", nullable = false)
//    private LocalDateTime regDt = LocalDateTime.now();
//
//}
