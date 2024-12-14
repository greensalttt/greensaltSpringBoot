package com.example.greenspringboot.board.dto;

import lombok.Data;
import java.util.Date;
import lombok.Builder;

@Data
@Builder
//빌더 어노테이션 사용하면 생성자 필요없다
public class BoardDto {
    private Integer bno;
    private Integer cId;
    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;
    private Integer commentCnt;
    private Boolean deleted;
    private Date regDt;
    private Date upDt;


//    public BoardDto() {
//    }
//
//    public BoardDto(Integer bno, Integer cId, String title, String content, String writer, Integer viewCnt, Integer commentCnt, Boolean deleted, Date regDt, Date upDt) {
//        this.bno = bno;
//        this.cId = cId;
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.viewCnt = viewCnt;
//        this.commentCnt = commentCnt;
//        this.deleted = deleted;
//        this.regDt = regDt;
//        this.upDt = upDt;
//    }

}
