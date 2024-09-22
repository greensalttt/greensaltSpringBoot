package com.example.greenspringboot.cust.repository;

import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  MyBatis에서는 Dao, JPA에서는 Repository
//Cust는 테이블 이름, Integer은 테이블의 pk 타입
public interface CustRepository extends JpaRepository<Cust, Integer> {
    boolean existsBycEmail(String cEmail);

    Optional<Cust> findBycEmail(String cEmail);
}
