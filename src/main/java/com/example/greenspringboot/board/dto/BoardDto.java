package com.example.greenspringboot.board.dto;

import lombok.Data;
import java.util.Date;
import lombok.Builder;

@Data
@Builder
//빌더 어노테이션 사용하면 생성자 필요없다
public class BoardDto {
    private Integer bno;
//    private Integer cId;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private Integer commentCnt;
    private Boolean deleted;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
}
