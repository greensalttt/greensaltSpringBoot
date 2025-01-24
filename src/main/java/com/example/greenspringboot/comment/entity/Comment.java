package com.example.greenspringboot.comment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString

/*MyBatis는 SQL로 쿼리를 작성하여 실행하는 방식
JPA는 객체 관계 매핑 중심으로 쿼리를 자동 생성
따라서 동일한 쿼리도 MyBatis와 JPA에서 작성되는 SQL은 다르다.
특히 부모 자식 관계에서*/

public class Comment {
    //    pk값 설정
    @Id
//    오토 인크리먼트 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cno;

    @Column(name="c_id")
    private Integer cId;

    private Integer bno;

    private Integer pcno;

    private String comment;

    private String commenter;

    @Builder.Default
    @Column(name= "reg_dt")
    private Date regDt = new Date();

    @Builder.Default
    @Column(name= "up_dt")
    private Date upDt = new Date();

    //    기본값 설정
    @Builder.Default
    @Column(name = "deleted")
    private Boolean deleted = false; // 기본값 false
}
