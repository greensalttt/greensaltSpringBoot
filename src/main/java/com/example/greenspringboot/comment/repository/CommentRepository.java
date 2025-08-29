package com.example.greenspringboot.comment.repository;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


/*
    c.deleted = false 삭제되지 않은 댓글만 가져오도록 조건을 설정

    COALESCE(c.parentComment.cno, c.cno)를 사용하여 대댓글을 부모 댓글과 함께 정렬
    부모 댓글이 존재하면 그 부모 댓글을 기준으로, 그렇지 않으면 자기 자신(cno)을 기준으로 정렬

    ORDER BY c.cno ASC는 댓글을 오름차순으로, 부모 댓글을 먼저 나오게 하며 대댓글은 그 아래에 나오는 방식으로 댓글 트리 구조를 형성
*/

    @Query("SELECT c FROM Comment c WHERE c.bno = :bno AND c.deleted = false ORDER BY COALESCE(c.pcno, c.cno), c.cno ASC")
    List<Comment> findAllCommentsWithReplies(Integer bno);



    Comment findByCno(Integer cno);


    Long countByCreatedByAndDeletedFalse(Integer createdBy);


    Long countByDeletedFalse();


    // JPQL에서 FROM 절은 엔티티(Comment)를 기준으로 데이터를 가져오고,
    // SELECT 절에서는 해당 엔티티의 일부 필드를 사용해
    // CommentDto 객체를 new 키워드로 직접 생성함
    // 즉, 엔티티를 직접 반환하지 않고 DTO로 가볍게 변환하여 리턴
    @Query("SELECT new com.example.greenspringboot.comment.dto.CommentDto(" +
            "c.cno, c.bno, c.pcno, c.comment, c.commenter, c.deleted, c.createdAt, c.createdBy, c.updatedAt, c.updatedBy) " +
            "FROM Comment c " +
            "WHERE c.createdBy = :createdBy AND c.deleted = false " +
            "ORDER BY c.cno DESC")
    List<CommentDto> findMyCommentList(@Param("createdBy") Integer createdBy);


    List<Comment> findAllByDeletedFalseOrderByCnoDesc();

    @Modifying
    @Query("UPDATE Comment c SET c.deleted = true WHERE c.bno = :bno")
    void deleteByBno(@Param("bno") Integer bno);

}
