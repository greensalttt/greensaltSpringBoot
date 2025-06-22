package com.example.greenspringboot.comment.service;

import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;

import java.util.List;

public interface CommentService{

    void write(CommentDto commentDto, Integer cId);

    void modify(CommentDto commentdto, Integer cno);

    List<Comment> list(Integer bno);

    void remove(CommentDto commentDto, Integer cno);
}
