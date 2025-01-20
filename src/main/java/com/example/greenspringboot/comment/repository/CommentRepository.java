package com.example.greenspringboot.comment.repository;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    List<Comment> findByBnoAndDeletedFalse(Integer bno);

//    @Query("SELECT c FROM Comment c WHERE c.bno = :bno AND c.deleted = false ORDER BY COALESCE(c.pcno, c.cno), c.cno ASC")
//    List<Comment> findAllCommentsWithReplies(Integer bno);


    @Query("SELECT c FROM Comment c WHERE c.bno = :bno AND c.deleted = false ORDER BY CASE WHEN c.pcno IS NULL THEN c.cno ELSE c.pcno END ASC, c.cno ASC")
    List<Comment> findByBnoAndDeletedFalse(@Param("bno") Integer bno);
    Comment findByCno(Integer cno);


}
