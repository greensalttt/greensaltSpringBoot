package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.cust.securityutils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlbumService albumService;

    @GetMapping("/adminpage")
    public String adminPage(Model model){
        adminService.albumPage(model);
        return "dashboard";
    }

//    @PostMapping("/adminpage")
//    public String login(String aId, String aPwd, HttpServletRequest request,  RedirectAttributes msg, Model model) {
//        if (!adminService.adminLogin(aId, aPwd, request, model)) {
//            msg.addFlashAttribute("adminLoginFail", "msg");
//            return "redirect:/login";
//        }
//        return "dashboard";
//    }

    @PostMapping("/adminlogout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/adminalbum")
    public String writePage(){
        return "adminAlbum";
    }

//    @PostMapping("/adminwrite")
//    public String albumWrite(AlbumDto albumDto, RedirectAttributes msg, @RequestParam("img") MultipartFile imgFile){
//        if(!albumService.write(albumDto, imgFile)){
//            msg.addFlashAttribute("adminWriteFail", "msg");
//            return "redirect:/adminalbum";
//        }
//        msg.addFlashAttribute("adminWrite", "msg");
//        return "redirect:/adminpage";
//    }

    @PostMapping("/adminwrite")
    public String albumWrite(
            @ModelAttribute AlbumDto albumDto,
            @RequestParam("img") MultipartFile imgFile,
            RedirectAttributes msg){


        if (!albumService.write(albumDto, imgFile)) {
            msg.addFlashAttribute("adminWriteFail", "msg");
            return "redirect:/adminalbum";
        }

        msg.addFlashAttribute("adminWrite", "msg");
        return "redirect:/adminpage";
    }

}
