package com.example.greenspringboot.comment.controller;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

//RESTful은 HTTP 메서드(GET, POST, PUT, DELETE, PATCH)를 사용하여 클라이언트와 서버간의 데이터를 주고 받는 형식
//MVC가 아닌 REST API 방식이기 때문에 댓글을 모델로 담아서 보여줄 필요가 없다. JSON으로 응답
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
        Integer cId = (Integer) session.getAttribute("cId");
        commentDto.setCreatedBy((Integer) session.getAttribute("cId"));
        commentDto.setBno(bno);
        commentService.write(commentDto, cId);

        return "redirect:/board";
    }

    // 댓글 수정
    // JSON body를 요청해서 수정하므로 RequestBody를 사용함(등록과 수정시에만, 삭제는 필요없음)
    @PatchMapping("/comments/{cno}")
    public String modify(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
        commentDto.setCreatedBy((Integer) session.getAttribute("cId"));
        commentService.modify(commentDto, cno);
        return "redirect:/board";
    }


    //    댓글 삭제
    @DeleteMapping("/comments/{cno}")
    public String remove(@PathVariable Integer cno, CommentDto commentDto, HttpSession session) {
        commentDto.setCreatedBy((Integer) session.getAttribute("cId"));
        commentService.remove(commentDto, cno);
        return "redirect:/board";
    }

}

