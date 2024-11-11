package com.example.greenspringboot.board.dto;

import lombok.Data;
import java.time.LocalDateTime;
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
    private Integer deleted;
    private Date regDt;
    private Date upDt;
}

//    생성자에 대한 이해가 더 필요
//    굳이 여러개 생성자가 필요 없다? 기본 생성자로 해결 가능한 부분은 기본 생성자로 해결해보기
//    public BoardDto(){}


//    public BoardDto(Integer cId, String title, String content, String writer, Integer deleted) {
//        this.cId = cId;
//        this.title = title;
//        this.content = content;
//        this.writer = writer;
//        this.deleted = deleted;
//    }
//}
