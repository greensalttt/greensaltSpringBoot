package com.example.greenspringboot.cust.repository;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.dto.CustDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//  MyBatis에서는 Dao, JPA에서는 Repository
//Cust는 테이블 이름, Integer은 테이블의 pk 타입
public interface CustRepository extends JpaRepository<Cust, Integer> {
//      고객번호 찾기
//    값이 있을 수도 없을 수도 있을 때 Optional 사용
//    Optional<Cust> findBycId(int cId);

//  이메일 중복
    boolean existsBycEmail(String cEmail);
//닉네임 중복
    boolean existsBycNick(String cNick);
//    로그인 이메일 찾기
    Optional<Cust> findBycEmail(String cEmail);
}
