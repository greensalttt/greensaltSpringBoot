package com.example.greenspringboot.admin.service;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.comment.repository.CommentRepository;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustRepository custRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PerformanceRepository performanceRepository;


    @Override
    public Boolean adminLogin(String aLoginId, String aPwd, HttpServletRequest request, Model model) {
        System.out.println("관리자 로그인 아이디: " + aLoginId);

        Admin admin = adminRepository.findByaLoginId(aLoginId);

        if (admin == null) {
            return false;
        }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(aPwd, admin.getAPwd())) {
                return false;
            }

            HttpSession session = request.getSession();
            session.setAttribute("aId", admin.getAId());

            long custCount = custRepository.count();
            long boardCount = boardRepository.count();
            long commentCount = commentRepository.count();
            long albumCount = albumRepository.count();

            model.addAttribute("custCount", custCount);
            model.addAttribute("boardCount", boardCount);
            model.addAttribute("commentCount", commentCount);
            model.addAttribute("albumCount", albumCount);

            return true;

    }

    @Override
    public void adminPage(Model m){
        long custCount = custRepository.countByStatCd("M");
        long boardCount = boardRepository.countByDeletedFalse();
        long commentCount = commentRepository.countByDeletedFalse();
        long albumCount = albumRepository.count();
        long performanceCount = performanceRepository.count();


        m.addAttribute("custCount", custCount);
        m.addAttribute("boardCount", boardCount);
        m.addAttribute("commentCount", commentCount);
        m.addAttribute("albumCount", albumCount);
        m.addAttribute("performanceCount", performanceCount);


        System.out.println("회원수:" + custCount);
        System.out.println("게시글수:" + boardCount);
    }


    @Override
    public void custList(Model m){
    List<Cust> custs = custRepository.findAll();

        if (!custs.isEmpty()) {
            List<CustDto> custDtos = custs.stream()
                    .map(cust -> CustDto.builder()
                            .cId(cust.getCId())
                            .statCd(cust.getStatCd())
                            .cEmail(cust.getCEmail())
                            .cNick(cust.getCNick())
                            .cZip(cust.getCZip())
                            .cRoadA(cust.getCRoadA())
                            .cJibunA(cust.getCJibunA())
                            .cDetA(cust.getCDetA())
                            .loginDt(cust.getLoginDt())
                            .createdAt(cust.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());

            m.addAttribute("custDtos", custDtos);
            System.out.println("custDtos:" + custDtos);


        } else {
            System.out.println("회원 정보를 찾을 수 없습니다.");
        }
    }
}
