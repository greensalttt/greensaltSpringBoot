package com.example.greenspringboot.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class BoardDto {
    private  Integer bno;
    private  Integer c_id;
    private  String title;
    private  String content;
    private  String writer;
    private  int view_cnt;
    private  int comment_cnt;
    private Integer deleted;
    private LocalDateTime reg_date;
    private LocalDateTime up_date;
}
