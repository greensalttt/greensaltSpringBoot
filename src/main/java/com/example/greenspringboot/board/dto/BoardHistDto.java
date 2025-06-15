package com.example.greenspringboot.board.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
@Data
@Builder
public class BoardHistDto {
    private Integer bHistNum;
    private Integer bno;
    private Integer cId;
    private String bCngCd;
    private String bBf;
    private String bAf;
    private Date createdAt;
    private Integer createdBy;
}


