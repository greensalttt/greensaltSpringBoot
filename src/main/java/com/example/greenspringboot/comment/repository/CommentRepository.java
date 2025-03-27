package com.example.greenspringboot.comment.repository;
import com.example.greenspringboot.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {



    @Query("SELECT c FROM Comment c WHERE c.bno = :bno AND c.deleted = false ORDER BY COALESCE(c.parentComment.cno, c.cno), c.cno ASC")
    List<Comment> findAllCommentsWithReplies(Integer bno);


    Comment findByCno(Integer cno);


}
