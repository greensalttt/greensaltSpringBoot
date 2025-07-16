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
    @Column(name = "a_id", nullable = false)
    private Integer aId;

    @Column(name = "a_login_id")
    private String aLoginId;

    @Column(name = "a_nick")
    private String aNick;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Column(name = "a_pwd")
    private String aPwd;

    @Builder.Default
    private Date login_dt = new Date();

    @Builder.Default
    @Column(name = "visit_cnt")
    private int visitCnt = 0;

    @Builder.Default
    @Column(name = "created_at")
    private Date createdAt = new Date();

    //    @Builder.Default
    @Column(name = "created_by")
    private Integer createdBy;

    @Builder.Default
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    //    @Builder.Default
    @Column(name = "updated_by")
    private Integer updatedBy;


}
