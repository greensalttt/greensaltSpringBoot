package com.example.greenspringboot.comment.repository;


import com.example.greenspringboot.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
