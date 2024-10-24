package com.example.greenspringboot.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

    @RequestMapping("/board")
public class BoardController {

        @GetMapping("/list")
        public String boardList(){
            return "boardList";
        }
}
