package com.example.greenspringboot.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder

public class CommentHistDto {
        private Integer coHistNum;
        private Integer cno;
        private Integer bno;
        private Integer cId;
        private String coCngCd;
        private String coBf;
        private String coAf;
        private Date createdAt;
        private String createdBy;
    }
