package com.example.greenspringboot.admin.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.board.entity.BoardHist;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.dto.CommentHistDto;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.dto.CustHistDto;
import com.example.greenspringboot.notice.dto.NoticeDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request, Model model);

    Map<String, Long> getAdminStats();

    List<BoardDto> boardList();

    boolean adminRemove(Integer bno);


    List<CommentDto> commentList();

    boolean commentRemove(CommentDto commentDto, Integer cno);

//    void boardHist(Model m);

    List<BoardHistDto> boardHist();

    List<CommentHistDto> commentHist();

    List<CustHistDto> custHist();

    List<CustDto> custList();

//    List<NoticeDto> noticeList();
}

