package com.example.greenspringboot.cust.controller;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/")
    public String home(Model m){
        albumService.albumList(m);
        performanceService.performanceList(m);
        return "index";
    }
}
