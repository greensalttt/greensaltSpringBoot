package com.example.greenspringboot.cust.dto;

import lombok.Data;
import java.util.Date;
@Data
public class CustHistDto {
    private int cHistNum;
    private int cId;
    private String cCngCd;
    private String cBf;
    private String cAf;
    private Date frst_reg_dt;
    private String frst_reg_id;
    private Date last_mod_dt;
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


