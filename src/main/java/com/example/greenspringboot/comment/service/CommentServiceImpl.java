package com.example.greenspringboot.comment.service;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.entity.CommentHist;
import com.example.greenspringboot.comment.repository.CommentHistRepository;
import com.example.greenspringboot.comment.repository.CommentRepository;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private  CommentRepository commentRepository;

    @Autowired
    private CommentHistRepository commentHistRepository;


    @Autowired
    private CustRepository custRepository;

    @Override
        public List<Comment> list(Integer bno){

        return commentRepository.findAllCommentsWithReplies(bno);
    }

    @Override
    public void write(CommentDto commentDto, Integer cId) {
        Integer pcno = null;

        // 1. 부모 댓글 번호 확인 및 존재 검증
        if (commentDto.getPcno() != null) {
            Comment parentComment = commentRepository.findById(commentDto.getPcno())
                    .orElseThrow(() -> new IllegalArgumentException("부모 댓글이 존재하지 않습니다."));
            pcno = parentComment.getCno();
        }

        // 2. 사용자 정보 조회 (없으면 예외)
        Cust cust = custRepository.findById(cId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

        // 3. 댓글 엔티티 생성 및 저장
        Comment comment = Comment.builder()
                .bno(commentDto.getBno())
                .pcno(pcno)
                .comment(commentDto.getComment())
                .commenter(cust.getCNick())
                .createdBy(commentDto.getCreatedBy())
                .build();

        commentRepository.save(comment);

        System.out.println("commentDto:" + commentDto);
    }



    @Override
    public void modify(CommentDto commentDto, Integer cno) {
        Comment comment = commentRepository.findByCno(cno);
        String oldValue = comment.getComment();  // 예를 들어, content 필드를 수정한다고 가정
        comment.setComment(commentDto.getComment());  // 수정된 댓글 내용으로 변경
        commentRepository.save(comment);  // 저장

        String newValue = comment.getComment();  // 수정된 댓글 내용
        String changeCode = "content";  // 예를 들어, content 필드를 수정한다고 가정
        addCommentHist(commentDto, oldValue, newValue, changeCode);
    }

    @Override
    public void remove(CommentDto commentDto, Integer cno) {
        Comment comment = commentRepository.findByCno(cno);
        comment.setDeleted(true);
        commentRepository.save(comment);

        String oldValue = comment.getComment();  // 예를 들어, content 필드를 수정한다고 가정
        String newValue = "null";  // 수정된 댓글 내용
        String changeCode = "DELETE";  // 예를 들어, content 필드를 수정한다고 가정
       addCommentHist(commentDto, oldValue, newValue, changeCode);
    }


    private void addCommentHist(CommentDto commentDto, String oldValue, String newValue, String changeCode) {
        if (!oldValue.equals(newValue)) {
            CommentHist commentHist = CommentHist.builder()
                    .cno(commentDto.getCno())
                    .bno(commentDto.getBno())
                    .cId(commentDto.getCreatedBy())
                    .coCngCd(changeCode)
                    .coBf(oldValue)
                    .coAf(newValue)
                    .build();

            commentHistRepository.save(commentHist);
        }}

}
