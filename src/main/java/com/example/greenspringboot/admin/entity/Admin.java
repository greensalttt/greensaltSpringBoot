package com.example.greenspringboot.admin.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder

//관리자의 정보를 화면에 띄울 일은 없어서 DTO는 필요 없을듯?
public class Admin {

    @Id
    @NotBlank(message = "아이디를 입력해주세요.")
    @Column(name= "a_id", nullable = false)
    private String aId;

    @Builder.Default
    private String a_nick = "관리자";

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column(name= "a_pwd")
    private String aPwd;

    @Builder.Default
    @Column(name= "reg_dt")
    private Date regDt = new Date();

    @Builder.Default
    private Date login_dt = new Date();

    @Builder.Default
    @Column(name= "visit_cnt")
    private int visitCnt = 0;

}
