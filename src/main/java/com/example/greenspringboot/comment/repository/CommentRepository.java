package com.example.greenspringboot.comment.repository;


import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByBnoAndDeletedFalse(Integer bno);

    Comment findByCno(Integer cno);



}
