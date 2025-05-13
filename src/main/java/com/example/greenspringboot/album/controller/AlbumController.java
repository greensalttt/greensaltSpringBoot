package com.example.greenspringboot.album.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/album")
public class AlbumController {

    @GetMapping("/list")
    public String AlbumList(){
        return "albumList";
    }


    @GetMapping("/page")
    public String AlbumPage(){
        return "albumPage";
    }
}
