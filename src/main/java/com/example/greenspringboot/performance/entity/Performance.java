package com.example.greenspringboot.performance.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "performance")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Performance {

    //    pk값, 오토 인크리먼트 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pno;

    @Column(name = "a_id", nullable = false)
    private int aId;

    private String title;

    private String artist;

    private String region;

    private String genre;

    private String venue;

    private String duration;

    private String rating;

    private String date;

    private String content;

    private String img;

    @Builder.Default
    private Boolean deleted = false;

    @Builder.Default
    @Column(name= "reg_dt", nullable = false)
    private Date regDt = new Date();

    @Builder.Default
    @Column(name= "up_dt", nullable = false)
    private Date upDt = new Date();

}

