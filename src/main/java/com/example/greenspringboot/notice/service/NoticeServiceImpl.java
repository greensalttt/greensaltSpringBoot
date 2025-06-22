package com.example.greenspringboot.notice.service;
import com.example.greenspringboot.admin.dto.AdminDto;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.entity.Notice;
import com.example.greenspringboot.notice.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private AdminRepository adminRepository;

//    @Override
//    public boolean write(NoticeDto noticeDto, HttpSession session, Integer aId) {
//        try {
//
////            noticeDto.setCreatedBy((Integer) session.getAttribute("aId"));
//
//            Optional<Admin> optionalAdmin = adminRepository.findById(aId);
//                Admin admin = optionalAdmin.get(); // Optional에서 실제 Cust 객체를 꺼냄
//
//                Integer aId = admin.getAId();
//                String aNick = admin.getANick();
//
//                AdminDto adminDto = AdminDto.builder()
//                        .aId(aId);
//                        .aNick(aNick)
//                        .build();
//
//                Notice notice = Notice.builder()
//                        .nno(noticeDto.getNno())
//                        .title(noticeDto.getTitle())
//                        .content(noticeDto.getContent())
//                        .writer(adminDto.getANick())
//                        .createdBy(noticeDto.getCreatedBy())
//                        .build();
//
//                noticeRepository.save(notice);
//                return true;
//
//            } catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//        }

    @Override
    public boolean write(NoticeDto noticeDto, HttpSession session, Integer aId) {
        try {

            Admin admin = adminRepository.findById(aId)
            .orElseThrow(() -> new IllegalArgumentException("관리자를 찾을 수 없습니다."));

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
            return true;

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
