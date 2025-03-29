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

    // 대댓글 중에서 최고 조상을 찾는 관계이므로 다대일(Many-to-One) 관계를 사용
    @ManyToOne
    @JoinColumn(name = "pcno")  // 자기 테이블 내의 "pcno" 컬럼을 외래 키로 사용
    private Comment parentComment; // 각 댓글은 하나의 부모 댓글을 참조

    private String comment;

    private String commenter;

    @Builder.Default
    @Column(name= "reg_dt")
    private Date regDt = new Date();

    @Builder.Default
    @Column(name= "up_dt")
    private Date upDt = new Date();

    @Builder.Default
    private Boolean deleted = false;
}
