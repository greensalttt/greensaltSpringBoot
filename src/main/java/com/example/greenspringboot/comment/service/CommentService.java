package com.example.greenspringboot.comment.service;

import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;

import java.util.List;

public interface CommentService{

    void write(CommentDto commentdto);
    void modify(CommentDto commentdto, Integer cno);
    void toEntity(Comment comment, CommentDto commentDto);

    List<Comment> list(Integer bno);
}
