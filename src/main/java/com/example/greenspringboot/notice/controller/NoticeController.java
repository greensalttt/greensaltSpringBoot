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
//        try {
//            noticeService.noticeRead(nno, m);
//            return "notice";
//        } catch (Exception e) {
//            log.error("공지 읽기 실패", e);  // 로그는 상세하게
//            m.addAttribute("msg", "공지 읽기 실패");
//            return "errorPage"; // 예외 시 보여줄 별도의 뷰
//        }
//    }


//  글로벌 예외처리기 사용
    @GetMapping("/read")
    public String NoticeRead(Integer nno, Model m){
        noticeService.noticeRead(nno, m);
        return "notice";
    }


    @GetMapping("/write")
    public String noticeWritePage() {
        return "noticeInsertForm";
    }

    @PostMapping("/write")
    public String noticeWrite(@ModelAttribute NoticeDto noticeDto, HttpSession session, RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 등록할 수 없습니다.");
        }

        noticeService.write(noticeDto, session, aId);

        // ✅ 성공 메시지는 직접 추가
        msg.addFlashAttribute("msg", "공지사항 등록에 성공했습니다.");
        return "redirect:/admin/page";
    }

    @GetMapping("/manage")
    public String noticeManage(Model m) {
        adminService.noticeList(m);
        return "noticeManage";
    }

    @PostMapping("/remove")
    public String noticeRemove(Integer nno,
                               RedirectAttributes msg,
                               HttpSession session) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }

        noticeService.noticeRemove(nno);

        // ✅ 성공 메시지 유지
        msg.addFlashAttribute("msg", "공지사항 삭제에 성공했습니다.");
        return "redirect:/admin/notice_manage";
    }

    @GetMapping("/edit")
    public String noticeEdit(Integer nno, Model m) {
        noticeService.noticeRead(nno, m);  // 공지 데이터 조회
        return "noticeEdit";               // 수정 폼 페이지 반환
    }

    @PostMapping("/modify")
    public String noticeModify(NoticeDto noticeDto, Integer nno, HttpSession session, RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 수정할 수 없습니다.");
        }

        noticeService.modify(nno, noticeDto);

        // ✅ 성공 메시지 유지
        msg.addFlashAttribute("msg", "공지사항 수정에 성공했습니다.");
        return "redirect:/admin/notice_manage";
    }


}
