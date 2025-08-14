package com.example.greenspringboot.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;
import lombok.Builder;

@Data
@Builder
@AllArgsConstructor

//빌더 어노테이션 사용하면 생성자 필요없다
public class BoardDto {
    private Integer bno;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private Integer commentCnt;
    private Boolean deleted;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private String updatedBy;
}
