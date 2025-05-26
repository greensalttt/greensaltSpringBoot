package com.example.greenspringboot.cust.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class CustDto {
    private Integer cId;
    private String cStatCd;
    private String cEmail;
    private String cPwd;
    private String cName;
    private String cNick;
    private String cBirth;
    private String cGnd;
    private String cPhn;
    private String cZip;
    private String cRoadA;
    private String cJibunA;
    private String cDetA;
    private String smsAgr;
    private String emailAgr;
    private Date regDt;
    private Date login_dt;
    private Long visitCnt;
    private Date frst_reg_dt;
    private String frst_reg_id;
    private Date last_mod_dt;
    private String last_mod_id;
    private Long boardCount;
    private Long commentCount;
}