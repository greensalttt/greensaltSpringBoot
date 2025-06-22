package com.example.greenspringboot.admin.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
public class AdminDto {
    private Integer aId;
    private String aLoginId;
    private String aNick;
    private String aPwd;
    private Date loginDt;
    private int visitCnt;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
}
