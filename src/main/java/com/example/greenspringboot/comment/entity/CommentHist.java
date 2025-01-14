package com.example.greenspringboot.comment.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime frst_reg_dt = LocalDateTime.now();

    @Builder.Default
    private String frst_reg_id = "minwook";

    @Builder.Default
    private LocalDateTime last_mod_dt = LocalDateTime.now();

    @Builder.Default
    private String last_mod_id = "minwook";

}
