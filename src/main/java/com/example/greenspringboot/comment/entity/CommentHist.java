package com.example.greenspringboot.comment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "comment_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommentHist {
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "co_hist_num", nullable = false)
    private Integer coHistNum;

    @Column(name = "cno", nullable = false)
    private Integer cno;

    @Column(name = "c_id", nullable = false)
    private Integer cId;

    @Column(name = "bno", nullable = false)
    private Integer bno;

    @Column(name = "co_bf", nullable = false)
    private String coBf;

    @Column(name = "co_af", nullable = false)
    private String coAf;

    @Builder.Default
    @Column(name= "created_at")
    private Date createdAt = new Date();

//    @Builder.Default
    @Column(name= "created_by")
    private Integer createdBy;

}
