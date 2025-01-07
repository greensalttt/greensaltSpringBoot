package com.example.greenspringboot.comment.service;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private  CommentRepository commentRepository;


    @Override
    public List<Comment> list(Integer bno){
        return commentRepository.findByBno(bno);
    }

    @Override
    public void write(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .cno(commentDto.getCno())
                .cId(commentDto.getCId())
                .bno(commentDto.getBno())
                .comment(commentDto.getComment())
                .commenter(commentDto.getCommenter())
                .build();
        // Board 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        commentRepository.save(comment);
    }
}
