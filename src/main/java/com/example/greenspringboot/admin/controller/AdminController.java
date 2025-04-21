package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.cust.securityutils.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/adminlogin")
    public String login(String aId, String aPwd, HttpServletRequest request,  RedirectAttributes msg) {
        if (!adminService.adminLogin(aId, aPwd, request)) {
            msg.addFlashAttribute("adminLoginFail", "msg");
            return "redirect:/login";
        }
        return "dashborad";
    }
}
