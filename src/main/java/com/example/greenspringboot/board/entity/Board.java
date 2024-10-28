package com.example.greenspringboot.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Board {
    //    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bno", nullable = false)
    private Integer bno;
}
