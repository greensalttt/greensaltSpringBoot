package com.example.greenspringboot.notice.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notice")
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/list")
    public String NoticeList(Model m) {
        adminService.noticeList(m);
        return "noticeList";
    }

    @GetMapping("/read")
    public String NoticeRead(Integer nno, Model m){
        noticeService.noticeRead(nno, m);
        return "notice";
    }
}
