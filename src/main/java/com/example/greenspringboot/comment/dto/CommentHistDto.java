package com.example.greenspringboot.comment.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder

public class CommentHistDto {
        private Integer coHistNum;
        private Integer cno;
        private Integer cId;
        private Integer bno;
        private String coBf;
        private String coAf;
        private Date frst_reg_dt;
        private String frst_reg_id;
        private Date last_mod_dt;
        private String last_mod_id;
    }
