package com.example.greenspringboot.comment.controller;
import com.example.greenspringboot.comment.dto.CommentDto;
//import com.example.greenspringboot.comment.dto.CommentHistDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.entity.CommentHist;
import com.example.greenspringboot.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

//RESTful은 HTTP 메서드(GET, POST, PUT, DELETE, PATCH)를 사용하여 클라이언트와 서버간의 데이터를 주고 받는 형식
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;


    @GetMapping("/comments")
    public List<Comment> list(Integer bno) {
        return commentService.list(bno);
    }

    // 댓글을 등록하는 메서드
    @PostMapping("/comments")
    public String write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
        commentDto.setCId((Integer) session.getAttribute("cId"));
        commentDto.setCommenter((String) session.getAttribute("cNick"));
        commentDto.setBno(bno);
        commentService.write(commentDto);

        return "redirect:/board";
    }
    // 댓글 수정
    @PatchMapping("/comments/{cno}")
    public String modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        commentDto.setCId((Integer) session.getAttribute("cId"));
        commentDto.setCno(cno);
        commentService.modify(commentDto, cno);
        return "redirect:/board";
    }

//    댓글 삭제
    @DeleteMapping("/comments/{cno}")
    public String remove(@PathVariable Integer cno) {
        commentService.remove(cno);
        return "redirect:/board";
    }
}

