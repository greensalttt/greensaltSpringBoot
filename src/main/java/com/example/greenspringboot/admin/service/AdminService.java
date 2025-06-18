package com.example.greenspringboot.admin.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.board.entity.BoardHist;
import com.example.greenspringboot.comment.dto.CommentDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request, Model model);

    void adminPage(Model model);

    void custList(Model m);

    void boardList(Model m);

//    boolean boardRemove(Integer bno);

//    boolean boardRemove(Integer bno, HttpSession session);

    boolean boardRemove(BoardDto boardDto, Integer bno);

    void commentList(Model m);

//    boolean commentRemove(Integer cno, HttpSession session);

//    boolean commentRemove(CommentDto commentDto, Integer cno, HttpSession session);

    boolean commentRemove(CommentDto commentDto, Integer cno);

    void boardHist(Model m);

    void commentHist(Model m);
}

