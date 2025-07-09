package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.service.NoticeService;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private PerformanceService performanceService;

//    뒤로가기 버튼
    @GetMapping("/page")
    public String adminPage(Model m){
        adminService.adminPage(m);
        return "adminPage";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

// 앨범 등록 페이지
@GetMapping("/album")
public String writePage() {
    return "albumInsertForm";
}

    // 앨범 등록 처리
    @PostMapping("/album_write")
    public String albumWrite(@ModelAttribute AlbumDto albumDto, HttpSession session, RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 등록할 수 없습니다.");
        }

        albumService.write(albumDto, aId);

        msg.addFlashAttribute("adminWrite", "msg");
        return "redirect:/admin/page";
    }

    // 앨범 관리 페이지(리스트)
    @GetMapping("/album_manage")
    public String albumManage(Model m) {
        albumService.albumList(m);
        return "albumManage";
    }

    // 앨범 삭제 처리
    @PostMapping("/album_remove")
    public String albumRemove(Integer ano, RedirectAttributes msg, HttpSession session) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }

        albumService.albumRemove(ano);
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/album_manage";
    }

    // 앨범 수정 페이지
    @GetMapping("/album_edit")
    public String albumEdit(Integer ano, Model m) {
        albumService.albumRead(ano, m);
        return "albumEdit";
    }

    // 앨범 수정 처리
    @PostMapping("/album_modify")
    public String albumModify(AlbumDto albumDto, Integer ano, MultipartFile imgFile, HttpSession session, RedirectAttributes msg) throws IOException{
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 수정할 수 없습니다.");
        }
        albumService.albumModify(albumDto, aId, ano, imgFile, session);
        msg.addFlashAttribute("modify", "msg");
        return "redirect:/admin/album_manage";
    }


    // 공연 관리 페이지
    @GetMapping("/performance_manage")
    public String performanceManage(Model m) {
        performanceService.performanceList(m);
        return "performanceManage";
    }

    // 공연 등록 페이지
    @GetMapping("/performance")
    public String performanceWritePage() {
        return "performanceInsertForm";
    }

    // 공연 등록 처리
    @PostMapping("/performance_write")
    public String performanceWrite(@ModelAttribute PerformanceDto performanceDto,
                                   HttpSession session,
                                   RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 등록할 수 없습니다.");
        }

        performanceService.write(performanceDto, session);

        msg.addFlashAttribute("performanceWrite", "msg");
        return "redirect:/admin/page";
    }

    // 공연 수정 페이지
    @GetMapping("/performance_edit")
    public String performanceEdit(Integer pno, Model m) {
        performanceService.performanceRead(pno, m);
        return "performanceEdit";
    }

    // 공연 수정 처리
    @PostMapping("/performance_modify")
    public String performanceModify(PerformanceDto performanceDto,
                                    MultipartFile imgFile,
                                    HttpSession session,
                                    RedirectAttributes msg) throws IOException {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 수정할 수 없습니다.");
        }

        performanceService.performanceModify(performanceDto, imgFile, session);

        msg.addFlashAttribute("performanceModify", "msg");
        return "redirect:/admin/performance_manage";
    }

    // 공연 삭제 처리
    @PostMapping("/performance_remove")
    public String performanceRemove(Integer pno,
                                    RedirectAttributes msg,
                                    HttpSession session) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }

        performanceService.performanceRemove(pno);

        msg.addFlashAttribute("performanceRemove", "msg");
        return "redirect:/admin/performance_manage";
    }

//    게시글
    @GetMapping("/board_manage")
    public String boardManage(Model m){
        adminService.boardList(m);
        return "boardManage";
    }

    @PostMapping("/board_remove")
    public String boardRemove(BoardDto boardDto, Integer bno, RedirectAttributes msg, HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }
        adminService.boardRemove(boardDto, bno);
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/board_manage";
    }

    @GetMapping("/board_hist")
    public String boardHistory(Model m){
        adminService.boardHist(m);
        return "boardHist";
    }

//    회원
    @GetMapping("/cust_list")
    public String custList(Model m){
        adminService.custList(m);
        return "custList";
    }

    @GetMapping("/cust_hist")
    public String custHistory(Model m){
        adminService.custHist(m);
        return "custHist";
    }



//    댓글
    @GetMapping("/comment_manage")
    public String commentManage(Model m){
        adminService.commentList(m);
        return "commentManage";
    }

    @PostMapping("/comment_remove")
    public String commentRemove(CommentDto commentDto, Integer cno, RedirectAttributes msg, HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }

        adminService.commentRemove(commentDto,cno);
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/comment_manage";
    }

    @GetMapping("/comment_hist")
    public String commentHistory(Model m){
        adminService.commentHist(m);
        return "commentHist";
    }


//    공지사항
    @GetMapping("/notice_write")
    public String noticeWritePage() {
        return "noticeInsertForm";
    }

    @PostMapping("/notice_write")
    public String noticeWrite(@ModelAttribute NoticeDto noticeDto, HttpSession session, RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 등록할 수 없습니다.");
        }

        noticeService.write(noticeDto, session, aId);

        msg.addFlashAttribute("msg", "공지사항 등록에 성공했습니다.");
        return "redirect:/admin/page";
    }

    @GetMapping("/notice_manage")
    public String noticeManage(Model m) {
        adminService.noticeList(m);
        return "noticeManage";
    }

    @PostMapping("/notice_remove")
    public String noticeRemove(Integer nno,
                               RedirectAttributes msg,
                               HttpSession session) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 삭제할 수 없습니다.");
        }

        noticeService.noticeRemove(nno);

        msg.addFlashAttribute("msg", "공지사항 삭제에 성공했습니다.");
        return "redirect:/admin/notice_manage";
    }

    @GetMapping("/notice_edit")
    public String noticeEdit(Integer nno, Model m) {
        noticeService.noticeRead(nno, m);
        return "noticeEdit";
    }

    @PostMapping("/notice_modify")
    public String noticeModify(NoticeDto noticeDto, Integer nno, HttpSession session, RedirectAttributes msg) {
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            throw new IllegalArgumentException("테스트 아이디는 수정할 수 없습니다.");
        }

        noticeService.modify(nno, noticeDto);

        msg.addFlashAttribute("msg", "공지사항 수정에 성공했습니다.");
        return "redirect:/admin/notice_manage";
    }


}
