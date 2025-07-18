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
        Comment parentComment = null;
        if (commentDto.getPcno() != null) {
            parentComment = commentRepository.findById(commentDto.getPcno()).orElse(null);
        }

        Optional<Cust> optionalCust = custRepository.findById(cId);

        if (optionalCust.isPresent()) {
            Cust cust = optionalCust.get(); // Optional에서 실제 Cust 객체를 꺼냄

            String cNick = cust.getCNick();

            CustDto custDto = CustDto.builder()
                    .cNick(cNick)
                    .build();

            // comment 엔티티 빌드
            Comment comment = Comment.builder()
                    .bno(commentDto.getBno())
                    .parentComment(parentComment)  // parentComment가 null일 수도 있음
                    .comment(commentDto.getComment())
                    .commenter(custDto.getCNick())
                    .createdBy(commentDto.getCreatedBy())
//                    .updatedBy(commentDto.getCreatedBy())
                    .build();

            // comment 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
            commentRepository.save(comment);

            System.out.println("commentDto:" + commentDto);
        }
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
