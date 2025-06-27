package com.example.greenspringboot.notice.service;
import com.example.greenspringboot.admin.dto.AdminDto;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.entity.Notice;
import com.example.greenspringboot.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private AdminRepository adminRepository;


    @Override
    public void write(NoticeDto noticeDto, HttpSession session, Integer aId) {
        Admin admin = adminRepository.findById(aId)
//                orElseThrow는 pk로 데이터를 가져올떄 사용
                .orElseThrow(IllegalArgumentException::new);

        String aNick = admin.getANick();

        AdminDto adminDto = AdminDto.builder()
                .aNick(aNick)
                .build();

        Notice notice = Notice.builder()
                .nno(noticeDto.getNno())
                .title(noticeDto.getTitle())
                .content(noticeDto.getContent())
                .writer(adminDto.getANick())
                .createdBy(aId)
                .build();

        noticeRepository.save(notice);
    }


    @Transactional
    @Override
    public void noticeRead(Integer nno, Model m) {
        Notice notice = noticeRepository.findById(nno)
                .orElseThrow(IllegalArgumentException::new);

        noticeRepository.incrementViewCnt(nno);

        if (notice != null) {
            String title = notice.getTitle();
            String content = notice.getContent().replace("\n", "<br/>");

            NoticeDto noticeDto = NoticeDto.builder()
                    .nno(nno)
                    .writer(notice.getWriter())
                    .title(title)
                    .content(content)
                    .viewCnt(notice.getViewCnt())
                    .createdAt(notice.getCreatedAt())
                    .build();

            m.addAttribute("noticeDto", noticeDto);

            System.out.println("noticeDto:" + noticeDto);
        } else {
            System.out.println("공지사항을 찾을 수 없습니다.");
        }
    }


    @Override
    public void noticeRemove(Integer nno){
        Notice notice = noticeRepository.findById(nno)
                .orElseThrow(IllegalArgumentException::new);

        notice.setDeleted(true);
        noticeRepository.save(notice);
    }


}
