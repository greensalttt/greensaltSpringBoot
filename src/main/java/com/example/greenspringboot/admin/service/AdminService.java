package com.example.greenspringboot.admin.service;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {


    Boolean adminLogin(String aId, String aPwd, HttpServletRequest request);
}
