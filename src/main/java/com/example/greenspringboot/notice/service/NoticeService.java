package com.example.greenspringboot.notice.service;

import com.example.greenspringboot.notice.dto.NoticeDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface NoticeService {
    void write(NoticeDto noticeDto, Integer aId);

    void noticeRead(Integer nno, Model m);

    @Transactional
    NoticeDto noticeEditRead(Integer nno);

    void noticeRemove(Integer nno);

    void modify(Integer nno, NoticeDto noticeDto);

    List<NoticeDto> noticeList();

    void noticeList(Model m);
}
