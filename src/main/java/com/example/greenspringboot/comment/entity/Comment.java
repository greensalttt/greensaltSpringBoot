package com.example.greenspringboot.comment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class Comment {
    //    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cno", nullable = false)
    private Integer cno;

    @Column(name="c_id", nullable = false)
    private Integer cId;

    @Column(name="bno", nullable = false)
    private Integer bno;

    @Column(name="pcno")
    private Integer pcno;

    @Column(name="comment", nullable = false)
    private String comment;

    @Column(name="commenter", nullable = false)
    private String commenter;

    @Builder.Default
    @Column(name= "reg_dt", nullable = false)
    private Date regDt = new Date();

    @Builder.Default
    @Column(name= "up_dt", nullable = false)
//    private LocalDateTime upDt = LocalDateTime.now();
    private Date upDt = new Date();

    //    기본값 설정
    @Builder.Default
    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false; // 기본값 false

}
