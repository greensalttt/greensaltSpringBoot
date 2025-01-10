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
        // comment 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        commentRepository.save(comment);
    }

    @Override
    public void modify(CommentDto commentDto, Integer cno) {
        Comment comment = commentRepository.findByCno(cno);
        toEntity(comment, commentDto);
        commentRepository.save(comment);
    }

    @Override
    public void toEntity(Comment comment, CommentDto commentDto) {
        if (commentDto.getCno() != null) {
            comment.setCno(commentDto.getCno());
        }
        if (commentDto.getCId() != null) {
            comment.setCId(commentDto.getCId());
        }
        if (commentDto.getBno() != null) {
            comment.setBno(commentDto.getBno());
        }
        if (commentDto.getPcno() != null) {
            comment.setPcno(commentDto.getPcno());
        }
        if (commentDto.getComment() != null) {
            comment.setComment(commentDto.getComment());
        }
        if (commentDto.getCommenter() != null) {
            comment.setCommenter(commentDto.getCommenter());
        }
        if (commentDto.getRegDt() != null) {
            comment.setRegDt(commentDto.getRegDt());
        }
        if (commentDto.getUpDt() != null) {
            comment.setUpDt(commentDto.getUpDt());
        }
        if (commentDto.getDeleted() != null) {
            comment.setDeleted(commentDto.getDeleted());
        }
    }


}
