package com.example.greenspringboot.admin.service;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.comment.repository.CommentRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

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

    @Override
    public Boolean adminLogin(String aLoginId, String aPwd, HttpServletRequest request, Model model) {
        System.out.println("관리자 로그인 아이디: " + aLoginId);

        Admin admin = adminRepository.findByaLoginId(aLoginId);

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
    public void adminPage(Model model){
        long custCount = custRepository.countBycStatCd("M");
        long boardCount = boardRepository.countByDeletedFalse();
        long commentCount = commentRepository.countByDeletedFalse();
        long albumCount = albumRepository.count();

        model.addAttribute("custCount", custCount);
        model.addAttribute("boardCount", boardCount);
        model.addAttribute("commentCount", commentCount);
        model.addAttribute("albumCount", albumCount);
    }
}
