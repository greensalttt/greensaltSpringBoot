package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlbumService albumService;

//    @Autowired
//    private BoardService boardService;

    @Autowired
    private PerformanceService performanceService;

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

    @GetMapping("/album")
    public String writePage(){
        return "albumInsertForm";
    }


    @PostMapping("/write")
    public String albumWrite(@ModelAttribute AlbumDto albumDto, HttpSession session, RedirectAttributes msg){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/album";
        }


        if (!albumService.write(albumDto, session)) {
            msg.addFlashAttribute("adminWriteFail", "msg");
            return "redirect:/admin/album";
        }

        msg.addFlashAttribute("adminWrite", "msg");
        return "redirect:/admin/page";
    }


    @GetMapping("/album_manage")
    public String albumManage(Model m){
        albumService.albumList(m);
        return "albumManage";
    }

    @GetMapping("/performance_manage")
    public String performanceManage(Model m){
        performanceService.performanceList(m);
        return "performanceManage";
    }

    @GetMapping("/performance")
    public String performanceWritePage(){
        return "performanceInsertForm";
    }

    @PostMapping("/performance_write")
    public String performanceWrite(@ModelAttribute PerformanceDto performanceDto, HttpSession session, RedirectAttributes msg){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/performance";
        }

        if (!performanceService.write(performanceDto, session)) {
            msg.addFlashAttribute("performanceWriteFail", "msg");
            return "redirect:/admin/performance";
        }

        msg.addFlashAttribute("performanceWrite", "msg");
        return "redirect:/admin/page";
    }



    @GetMapping("/cust_list")
    public String custList(Model m){
        adminService.custList(m);
        return "custList";
    }

    @PostMapping("/album_remove")
    public String albumRemove(Integer ano, RedirectAttributes msg, HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/album_manage";
        }

        if(!albumService.albumRemove(ano)){
            msg.addFlashAttribute("removeFail", "msg");
            return "redirect:/admin/album_manage";
        }
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/album_manage";
    }

    @GetMapping("/album_edit")
    public String albumEdit(Integer ano, Model m){
        albumService.albumRead(ano, m);
        return "albumEdit";
    }

    @PostMapping("/album_modify")
    public String albumModify(AlbumDto albumDto, MultipartFile imgFile, HttpSession session, RedirectAttributes msg) throws IOException {

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/album_edit";
        }

        if(!albumService.albumModify(albumDto, imgFile, session)){
            msg.addFlashAttribute("modifyFail", "msg");
            return "redirect:/admin/album_edit";
        }
        msg.addFlashAttribute("modify", "msg");
        return "redirect:/admin/album_manage";
    }


    @GetMapping("/performance_edit")
    public String performanceEdit(Integer pno, Model m){
        performanceService.performanceRead(pno, m);
        return "performanceEdit";
    }

    @PostMapping("/performance_modify")
    public String performanceModify(PerformanceDto performanceDto, MultipartFile imgFile, HttpSession session, RedirectAttributes msg) throws IOException {

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/performance_edit";
        }

        if(!performanceService.performanceModify(performanceDto, imgFile, session)){
            msg.addFlashAttribute("performanceModifyFail", "msg");
            return "redirect:/admin/performance_edit";
        }
        msg.addFlashAttribute("performanceModify", "msg");
        return "redirect:/admin/performance_manage";
    }

    @PostMapping("/performance_remove")
    public String performanceRemove(Integer pno, RedirectAttributes msg,HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/performance_manage";
        }

        if(!performanceService.performanceRemove(pno)){
            msg.addFlashAttribute("performanceRemoveFail", "msg");
            return "redirect:/admin/performance_manage";
        }
        msg.addFlashAttribute("performanceRemove", "msg");
        return "redirect:/admin/performance_manage";
    }


    @GetMapping("/board_manage")
    public String boardManage(Model m){
        adminService.boardList(m);
        return "boardManage";
    }

    @PostMapping("/board_remove")
    public String boardRemove(BoardDto boardDto, Integer bno, RedirectAttributes msg, HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/board_manage";
        }

        if(!adminService.boardRemove(boardDto, bno)){
            msg.addFlashAttribute("removeFail", "msg");
            return "redirect:/admin/board_manage";
        }
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/board_manage";
    }


    @GetMapping("/comment_manage")
    public String commentManage(Model m){
        adminService.commentList(m);
        return "commentManage";
    }

    @PostMapping("/comment_remove")
    public String commentRemove(CommentDto commentDto, Integer cno, RedirectAttributes msg, HttpSession session){

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            msg.addFlashAttribute("testAid", "msg");
            return "redirect:/admin/comment_manage";
        }

        if(!adminService.commentRemove(commentDto,cno)){
            msg.addFlashAttribute("removeFail", "msg");
            return "redirect:/admin/comment_manage";
        }
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/comment_manage";
    }


    @GetMapping("/board_hist")
    public String boardHistory(Model m){
        adminService.boardHist(m);
        return "boardHist";
    }

    @GetMapping("/comment_hist")
    public String commentHistory(Model m){
        adminService.commentHist(m);
        return "commentHist";
    }

    @GetMapping("/cust_hist")
    public String custHistory(Model m){
        adminService.custHist(m);
        return "custHist";
    }
}
