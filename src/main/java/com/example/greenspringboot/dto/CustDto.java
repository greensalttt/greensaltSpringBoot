package com.example.greenspringboot.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
public class CustDto {
    private int c_id;
    private String c_grd_cd;
    private String c_stat_cd;
    private String cEmail;
    private String c_pwd;
    private String c_name;
    private String c_nm;
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

    public CustDto(String cEmail, String c_pwd, String c_name, String c_nm, String c_birth, char c_gnd, String c_phn, String c_zip, String c_road_a, String c_jibun_a, String c_det_a, String sms_agr, String email_agr) {
        this.cEmail = cEmail;
        this.c_pwd = c_pwd;
        this.c_name = c_name;
        this.c_nm = c_nm;
        this.c_birth = c_birth;
        this.c_gnd = c_gnd;
        this.c_phn = c_phn;
        this.c_zip = c_zip;
        this.c_road_a = c_road_a;
        this.c_jibun_a = c_jibun_a;
        this.c_det_a = c_det_a;
        this.sms_agr = sms_agr;
        this.email_agr = email_agr;
    }

    public CustDto(String c_name, String c_nm, String grd_name, String tot_amt, Date login_dt, int visit_cnt, Date reg_dt){
        this.c_name = c_name;
        this.c_nm = c_nm;
        this.grd_name = grd_name;
        this.tot_amt = tot_amt;
        this.login_dt = login_dt;
        this.visit_cnt = visit_cnt;
        this.reg_dt = reg_dt;
    }
}