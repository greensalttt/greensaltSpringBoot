package com.example.greenspringboot.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class CommentDto {
    private Integer cno;
    private Integer cId;
    private Integer bno;
    private Integer pcno;
    private String  comment;
    private String  commenter;
    private Date    regDt;
    private Date    upDt;
    private Integer deleted;


}