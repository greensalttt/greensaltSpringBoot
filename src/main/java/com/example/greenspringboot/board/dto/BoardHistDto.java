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
    private Date frst_reg_dt;
    private String frst_reg_id;
    private Date last_mod_dt;
    private String last_mod_id;

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


