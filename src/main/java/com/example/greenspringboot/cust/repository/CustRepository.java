package com.example.greenspringboot.cust.repository;

import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepository extends JpaRepository<Cust, Integer> {
}
