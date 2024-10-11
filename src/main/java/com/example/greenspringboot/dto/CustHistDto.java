package com.example.greenspringboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@Data
public class CustHistDto {
    private int cHistNum;
    private int cId;
    private String cCngCd;
    private String cBf;
    private String cAf;
    private LocalDateTime frst_reg_dt;
    private String frst_reg_id;
    private LocalDateTime last_mod_dt;
    private String last_mod_id;

    public CustHistDto(){}

    public CustHistDto(int cHistNum, int cId, String cCngCd, String cBf, String cAf){
        this.cHistNum = cHistNum;
        this.cId = cId;
        this.cCngCd = cCngCd;
        this.cBf = cBf;
        this.cAf = cAf;
    }
}


