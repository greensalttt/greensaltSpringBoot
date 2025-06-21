package com.example.greenspringboot.notice.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "notice")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Notice {
    //    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nno;

    private String title;

    private String content;

    private String writer;

    @Builder.Default
    @Column(name="view_cnt", nullable = false)
    private Integer viewCnt = 0;

    //    기본값 설정
    @Builder.Default
    private Boolean deleted = false; // 기본값 false

    @Builder.Default
    @Column(name= "created_at")
    private Date createdAt = new Date();

    @Column(name= "created_by")
    private Integer createdBy;

    @Builder.Default
    @Column(name= "updated_at")
    private Date updatedAt = new Date();

    @Builder.Default
    @Column(name= "updated_by")
    private String updatedBy = "admin";
}
