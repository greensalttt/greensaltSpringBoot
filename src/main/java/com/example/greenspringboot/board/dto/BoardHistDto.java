package com.example.greenspringboot.board.dto;

import lombok.Data;
import java.util.Date;
@Data
public class BoardHistDto {
    private Integer bHistNum;
    private Integer bno;
    private Integer cId;
    private String bCngCd;
    private String bBf;
    private String bAf;
    private Date createdAt;
    private Integer createdBy;

    public BoardHistDto(){}

    public BoardHistDto(Integer bHistNum, Integer bno, Integer cId, String bCngCd, String bBf, String bAf){
        this.bHistNum = bHistNum;
        this.bno = bno;
        this.cId = cId;
        this.bCngCd = bCngCd;
        this.bBf = bBf;
        this.bAf = bAf;
    }
}


