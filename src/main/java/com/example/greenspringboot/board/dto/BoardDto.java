package com.example.greenspringboot.board.dto;

import lombok.Data;
import java.time.LocalDateTime;


@Data
public class BoardDto {
    private  int bno;
    private  int c_id;
    private  String title;
    private  String content;
    private  String writer;
    private  int view_cnt;
    private  int comment_cnt;
    private int deleted;
    private LocalDateTime reg_dt;
    private LocalDateTime up_dt;

//    생성자에 대한 이해가 더 필요
//    굳이 여러개 생성자가 필요 없다? 기본 생성자로 해결 가능한 부분은 기본 생성자로 해결해보기
    public BoardDto(){}
}
