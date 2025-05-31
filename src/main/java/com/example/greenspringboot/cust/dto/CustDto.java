package com.example.greenspringboot.cust.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class CustDto {
    private Integer cId;
    private String statCd;
    private String cEmail;
    private String cPwd;
    private String cNick;
    private String cZip;
    private String cRoadA;
    private String cJibunA;
    private String cDetA;
    private Date loginDt;
    private Long visitCnt;
    private Date regDt;
    private String frst_reg_id;
    private Date last_mod_dt;
    private String last_mod_id;
    private Long boardCount;
    private Long commentCount;
}