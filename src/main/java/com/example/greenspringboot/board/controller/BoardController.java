package com.example.greenspringboot.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@RequestMapping("/board")
public class BoardController {

        @GetMapping("/list")
        public String boardList(HttpSession session){
            System.out.println("게시판 겟맵핑: " + session.getAttribute("cName"));
            return "boardList";
        }
}
