package com.example.greenspringboot.notice.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

//    @Autowired
//    private AdminService adminService;

//    고객 입장에서

    @GetMapping("/list")
    public String NoticeList(Model m) {
        noticeService.noticeList(m);
        return "noticeList";
    }

    @GetMapping("/read")
    public String NoticeRead(Integer nno, Model m){
        noticeService.noticeRead(nno, m);
        return "notice";
    }



}
