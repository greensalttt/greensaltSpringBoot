package com.example.greenspringboot.board.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Board {
    //    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;

    @Column(name="c_id", nullable = false)
    private Integer cId;

    private String title;

    private String content;

    private String writer;

    @Builder.Default
    @Column(name="view_cnt", nullable = false)
    private Integer viewCnt = 0;

    @Builder.Default
    @Column(name="comment_cnt", nullable = false)
    private Integer commentCnt = 0;

    //    기본값 설정
    @Builder.Default
    private Boolean deleted = false; // 기본값 false

    @Builder.Default
    @Column(name= "reg_dt", nullable = false)
    private Date regDt = new Date();


    @Builder.Default
    @Column(name= "up_dt", nullable = false)
    private Date upDt = new Date();

}
