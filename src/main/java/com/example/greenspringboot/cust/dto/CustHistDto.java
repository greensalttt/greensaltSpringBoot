package com.example.greenspringboot.cust.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;
@Data
@Builder
public class CustHistDto {
    private Integer cHistNum;
    private Integer cId;
    private String cCngCd;
    private String cBf;
    private String cAf;
    private Date createdAt;
    private String createdBy;
}


