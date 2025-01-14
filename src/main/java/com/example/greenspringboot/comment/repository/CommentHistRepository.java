package com.example.greenspringboot.comment.repository;

import com.example.greenspringboot.comment.entity.CommentHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentHistRepository extends JpaRepository<CommentHist, Integer> {

}
