package com.example.greenspringboot.cust.repository;

import com.example.greenspringboot.cust.entity.CustHist;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustHistRepository extends JpaRepository<CustHist, Integer> {
    }

