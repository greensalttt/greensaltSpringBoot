package com.example.greenspringboot.admin.service;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Boolean adminLogin(String aId, String aPwd, HttpServletRequest request) {
        System.out.println("관리자 로그인 아이디: " + aId);

        Optional<Admin> optionalAdmin = adminRepository.findById(aId);

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            // 비밀번호 불일치 시 false 반환
            if (!encoder.matches(aPwd, admin.getAPwd())) {
                return false;
            }

            // 비밀번호가 일치하면 세션 생성
            HttpSession session = request.getSession();
            session.setAttribute("aId", admin.getAId());
            return true;
        }

        // 해당 ID가 없는 경우
        return false;
    }

}
