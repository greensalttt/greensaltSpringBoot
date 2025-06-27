package com.example.greenspringboot.notice.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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

//    @GetMapping("/read")
//    public String NoticeRead(Integer nno, Model m){
//        noticeService.noticeRead(nno, m);
//        return "notice";
//    }

    @GetMapping("/read")
    public String NoticeRead(Integer nno, Model m){
        try {
            noticeService.noticeRead(nno, m);
            return "notice";
        } catch (Exception e) {
            log.error("공지 읽기 실패", e);  // 로그는 상세하게
            m.addAttribute("msg", "공지 읽기 실패");
            return "errorPage"; // 예외 시 보여줄 별도의 뷰
        }
    }

}
