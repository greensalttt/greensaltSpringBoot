package com.example.greenspringboot.admin.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request, Model model);

    void adminPage(Model model);

    void custList(Model m);

    void boardList(Model m);

//    boolean boardRemove(Integer bno);

    boolean boardRemove(Integer bno, HttpSession session);

    void commentList(Model m);

    boolean commentRemove(Integer cno, HttpSession session);
}
