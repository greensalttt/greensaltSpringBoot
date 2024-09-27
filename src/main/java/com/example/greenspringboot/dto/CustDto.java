package com.example.greenspringboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private String cNm;
    private String c_birth;
    private char c_gnd;
    private String c_phn;
    private String c_zip;
    private String c_road_a;
    private String c_jibun_a;
    private String c_det_a;
    private String sms_agr;
    private String email_agr;
    private Date reg_dt;
    private Date login_dt;
    private int visit_cnt;
    private String tot_amt;
    private Date frst_reg_dt;
    private String frst_reg_id;
    private Date last_mod_dt;
    private String last_mod_id;
    private String grd_name;

    public CustDto(){}


    public CustDto(String cPwd){
        this.cPwd = cPwd;
    }

    public CustDto(int cId, String cName, String cNm){
        this.cId = cId;
        this.cName = cName;
        this.cNm = cNm;
    }
}
