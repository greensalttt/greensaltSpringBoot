package com.example.greenspringboot.album.controller;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.paging.PageHandler;
import com.example.greenspringboot.board.paging.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Controller
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @GetMapping("/list")
    public String AlbumList(Model m, SearchCondition sc) {
        try {
            int totalCnt = albumService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);
            List<AlbumDto> list = albumService.getSearchResultPage(sc);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);


            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "albumList";
    }



    @GetMapping("/read")
    public String AlbumRead(Integer ano, Model m){
        albumService.albumRead(ano, m);
        return "album";
    }
}
