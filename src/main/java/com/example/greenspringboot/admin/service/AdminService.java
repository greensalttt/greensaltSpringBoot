package com.example.greenspringboot.admin.service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request, Model model);

    void albumPage(Model model);
}
