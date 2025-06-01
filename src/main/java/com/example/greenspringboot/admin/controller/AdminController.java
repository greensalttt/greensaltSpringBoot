package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.cust.securityutils.EncryptionUtil;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @GetMapping("/performance")
    public String performanceWritePage(){
        return "performanceInsertForm";
    }

    @PostMapping("/performance_write")
    public String performanceWrite(@ModelAttribute PerformanceDto performanceDto, HttpSession session, RedirectAttributes msg){

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

    @PostMapping("/remove")
    public String albumRemove(Integer ano, RedirectAttributes msg){
        if(!albumService.albumRemove(ano)){
            msg.addFlashAttribute("removeFail", "msg");
            return "redirect:/admin/manage";
        }
        msg.addFlashAttribute("remove", "msg");
        return "redirect:/admin/manage";
    }

    @GetMapping("/edit")
    public String albumEdit(Integer ano, Model m){
        albumService.albumRead(ano, m);
        return "albumEdit";
    }

    @PostMapping("/modify")
    public String albumModify(AlbumDto albumDto, MultipartFile imgFile, HttpSession session, RedirectAttributes msg) throws IOException {
        if(!albumService.albumModify(albumDto, imgFile, session)){
            msg.addFlashAttribute("modifyFail", "msg");
            return "redirect:/admin/edit";
        }
        msg.addFlashAttribute("modify", "msg");
        return "redirect:/admin/manage";
    }
}
