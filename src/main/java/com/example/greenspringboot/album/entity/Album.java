package com.example.greenspringboot.album.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "album")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Album {

    //    pk값, 오토 인크리먼트 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ano;

    @Column(name = "a_id", nullable = false)
    private int aId;

    private String domestic;

    private String type;

    private String genre;

    private String title;

    private String artist;

    private String content;

    private String img;

    private String released;

    @Builder.Default
    private Boolean deleted = false;

    @Builder.Default
    @Column(name= "reg_dt", nullable = false)
    private Date regDt = new Date();


    @Builder.Default
    @Column(name= "up_dt", nullable = false)
    private Date upDt = new Date();

}
