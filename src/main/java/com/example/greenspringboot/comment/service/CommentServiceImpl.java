package com.example.greenspringboot.comment.service;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.dto.CommentHistDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.entity.CommentHist;
import com.example.greenspringboot.comment.repository.CommentHistRepository;
import com.example.greenspringboot.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private CommentHistRepository commentHistRepository;


//    @Override
//    public List<Comment> list(Integer bno){
//        return commentRepository.findByBnoAndDeletedFalse(bno);
//    }

        public List<Comment> list(Integer bno){
        return commentRepository.findAllCommentsWithReplies(bno);
    }


    @Override
    public void write(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .cId(commentDto.getCId())
                .bno(commentDto.getBno())
                .pcno(commentDto.getPcno())
                .comment(commentDto.getComment())
                .commenter(commentDto.getCommenter())
                .build();

        // comment 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        commentRepository.save(comment);

        System.out.println("comment:" + comment);
    }

    @Override
    public void modify(CommentDto commentDto, Integer cno) {
        Comment comment = commentRepository.findByCno(cno);

        System.out.println("비엔오: " + comment.getBno());
        System.out.println("비엔오DTO: " + commentDto.getBno());


        // 수정 전 값 (이력 기록을 위해)
        String oldValue = comment.getComment();  // 예를 들어, content 필드를 수정한다고 가정
        comment.setComment(commentDto.getComment());  // 수정된 댓글 내용으로 변경

        commentRepository.save(comment);  // 저장

        // 수정 후 값 (이력 기록을 위해)
        String newValue = comment.getComment();  // 수정된 댓글 내용

        System.out.println("oldValue: " + oldValue);
        System.out.println("newValue: " + newValue);


        addCommentHist(commentDto, oldValue, newValue, comment);
    }

    private void addCommentHist(CommentDto commentDto, String oldValue, String newValue, Comment comment) {
        if (!oldValue.equals(newValue)) {

            System.out.println(commentDto);

            CommentHistDto commentHistDto = CommentHistDto.builder()
                    .cno(commentDto.getCno())
                    .cId(commentDto.getCId())
                    .bno(commentDto.getBno())
                    .coBf(oldValue)
                    .coAf(newValue)
                    .build();

            CommentHist commentHist = CommentHist.builder()
                    .cno(commentHistDto.getCno())
                    .cId(commentHistDto.getCId())
                    .bno(commentHistDto.getBno())
                    .coBf(commentHistDto.getCoBf())
                    .coAf(commentHistDto.getCoAf())
                    .build();

            // 이력 저장
            commentHistRepository.save(commentHist);
        }}

    @Override
    public void remove(Integer cno) {
        Comment comment = commentRepository.findByCno(cno);
        comment.setDeleted(true);
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

    @Override
    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .cno(comment.getCno())
                .cId(comment.getCId())
                .bno(comment.getBno())
                .pcno(comment.getPcno())
                .comment(comment.getComment())
                .commenter(comment.getCommenter())
                .regDt(comment.getRegDt())
                .upDt(comment.getUpDt())
                .deleted(comment.getDeleted())
                .build();
    }

}
