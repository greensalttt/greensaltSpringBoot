package com.example.greenspringboot.notice.service;

import com.example.greenspringboot.notice.dto.NoticeDto;

import javax.servlet.http.HttpSession;

public interface NoticeService {
    boolean write(NoticeDto noticeDto, HttpSession session, Integer aId);
}
