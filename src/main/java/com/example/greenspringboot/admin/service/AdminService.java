package com.example.greenspringboot.admin.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.board.entity.BoardHist;
import com.example.greenspringboot.comment.dto.CommentDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request, Model model);

//    void adminPage(Model model);

    Map<String, Long> getAdminStats();

    void custList(Model m);

    void boardList(Model m);

    void commentList(Model m);

    boolean commentRemove(CommentDto commentDto, Integer cno);

    void boardHist(Model m);

    void commentHist(Model m);

    void custHist(Model m);

    void noticeList(Model m);
}

