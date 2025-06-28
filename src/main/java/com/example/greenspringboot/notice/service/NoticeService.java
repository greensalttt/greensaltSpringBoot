package com.example.greenspringboot.notice.service;

import com.example.greenspringboot.notice.dto.NoticeDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface NoticeService {
    void write(NoticeDto noticeDto, HttpSession session, Integer aId);

    void noticeRead(Integer nno, Model m);

    void noticeRemove(Integer nno);

    void modify(Integer nno, NoticeDto noticeDto);
}
