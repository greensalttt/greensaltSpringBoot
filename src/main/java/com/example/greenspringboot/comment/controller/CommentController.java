package com.example.greenspringboot.comment.controller;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

//RESTful은 HTTP 메서드(GET, POST, PUT, DELETE, PATCH)를 사용하여 클라이언트와 서버간의 데이터를 주고 받는 형식
@RestController
public class CommentController {
    @Autowired
    CommentService commentService;


    // 댓글을 등록하는 메서드
    @PostMapping("/comments")
    public String write(@RequestBody CommentDto commentDto, Integer bno, HttpSession session) {
        Integer cId = (Integer) session.getAttribute("cId");
        commentDto.setCId(cId);
        commentDto.setCommenter((String) session.getAttribute("cNick"));
        commentDto.setBno(bno);
        System.out.println("commentDto = " + commentDto);
        commentService.write(commentDto);

        return "redirect:/board";
}


}