package com.example.greenspringboot.album.controller;

import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.album.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/list")
    public String AlbumList(Model m) {
            albumService.albumList(m);
            return "albumList";
    }


    @GetMapping("/read")
    public String AlbumRead(Integer ano, Model m){
        albumService.albumRead(ano, m);
        return "album";
    }
}
