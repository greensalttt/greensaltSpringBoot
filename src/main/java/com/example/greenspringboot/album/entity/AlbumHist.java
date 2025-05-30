package com.example.greenspringboot.album.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "album_hist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class AlbumHist{

@Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_hist_num", nullable = false)
    private Integer aHistNum;

    private Integer ano;

    @Column(name = "a_id", nullable = false)
    private Integer aId;

    @Column(name = "a_cng_cd", nullable = false)
    private String aCngCd;

    @Column(name = "a_bf", nullable = false)
    private String aBf;

    @Column(name = "a_af", nullable = false)
    private String aAf;

    @Builder.Default
    private LocalDateTime frst_reg_dt = LocalDateTime.now();

    @Builder.Default
    private String frst_reg_id = "minwook";

    @Builder.Default
    private LocalDateTime last_mod_dt = LocalDateTime.now();

    @Builder.Default
    private String last_mod_id = "minwook";
}