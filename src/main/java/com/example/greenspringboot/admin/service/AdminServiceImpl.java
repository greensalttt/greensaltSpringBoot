package com.example.greenspringboot.admin.service;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    private AdminRepository adminRepository;

    @Override
    public Boolean adminLogin(String aId, String aPwd) {

        System.out.println("관리자 로그인 아이디: " + aId);

        Optional<Admin> OptionalAdmin = adminRepository.findById(aId);

        if (OptionalAdmin.isPresent()) {
            Admin admin = OptionalAdmin.get(); // Optional에서 실제 Cust 객체를 꺼냄


            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            평문화와 암호화 비밀번호를 비교할 수 있는 메서드
            if (!encoder.matches(aPwd, admin.getAPwd())) {
                return false;
            }

        }
        return true;

    }
