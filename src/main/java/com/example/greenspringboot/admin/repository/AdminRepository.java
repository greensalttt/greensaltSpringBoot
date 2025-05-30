package com.example.greenspringboot.admin.repository;

import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByaLoginId(String aLoginId);

}
