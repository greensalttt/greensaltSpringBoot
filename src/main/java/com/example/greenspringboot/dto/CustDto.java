package com.example.greenspringboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Data
public class CustDto {
    private int cId;
    private String c_grd_cd;
    private String c_stat_cd;
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
    private LocalDateTime regDt;
    private Date login_dt;
    private int visit_cnt;
    private String tot_amt;
    private Date frst_reg_dt;
    private String frst_reg_id;
    private Date last_mod_dt;
    private String last_mod_id;

    public CustDto(){}


    public CustDto(String cPwd){
        this.cPwd = cPwd;
    }

    public CustDto(int cId, String cEmail, String cName, String cNick, String cBirth, String cGnd, String cPhn, String cZip, String cRoadA, String cJibunA, String cDetA, String smsAgr, String emailAgr, LocalDateTime regDt){
        this.cId = cId;
        this.cEmail = cEmail;
        this.cName = cName;
        this.cNick = cNick;
        this.cBirth = cBirth;
        this.cGnd = cGnd;
        this.cPhn = cPhn;
        this.cZip = cZip;
        this.cRoadA = cRoadA;
        this.cJibunA = cJibunA;
        this.cDetA = cDetA;
        this.smsAgr = smsAgr;
        this.emailAgr = emailAgr;
        this.regDt = regDt;
    }

    public CustDto(int cId, String cZip, String cRoadA, String cJibunA, String cDetA, String cBirth, String cPhn, String smsAgr, String emailAgr, LocalDateTime regDt){
        this.cId = cId;
        this.cZip = cZip;
        this.cRoadA = cRoadA;
        this.cJibunA = cJibunA;
        this.cDetA = cDetA;
        this.cBirth = cBirth;
        this.cPhn = cPhn;
        this.smsAgr = smsAgr;
        this.emailAgr = emailAgr;
        this.regDt = regDt;
    }
}
