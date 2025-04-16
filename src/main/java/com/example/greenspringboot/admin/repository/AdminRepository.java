package com.example.greenspringboot.admin.repository;

import com.example.greenspringboot.admin.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {

}
