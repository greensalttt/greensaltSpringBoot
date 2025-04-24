package com.example.greenspringboot.cust.repository;
import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

//  MyBatis에서는 Dao, JPA에서는 Repository
//Cust는 엔티티 이름, Integer은 테이블의 pk 타입
public interface CustRepository extends JpaRepository<Cust, Integer> {

//    JPA에서 제공해주는 CRUD 기본 메서드를 제외하고 커스텀을 할려면 여기서 메서드를 따로 생성해야됨
//    기본 메서드 4개: sava(저장, 업데이트), findById(조회),deleteById(삭제), findAll(모든 엔티티 조회)

    //  이메일 중복
    boolean existsBycEmail(String cEmail);
//닉네임 중복
    boolean existsBycNick(String cNick);
//    로그인 이메일 찾기
    Cust findBycEmail(String cEmail);

    @Modifying
    @Query("UPDATE Cust SET visitCnt = visitCnt + 1 WHERE cEmail = :cEmail")
    int incrementViSitCnt(@Param("cEmail") String cEmail);

//    @Query("SELECT c.visitCnt FROM Cust c WHERE c.cId = :cId")
//    long getTotalVisitCnt(@Param("cId") int cId);
//
//    @Query("SELECT c.cName FROM Cust c WHERE c.cId = :cId")
//    String findNameBycId(@Param("cId") int cId);
//
//    @Query("SELECT c.cNick FROM Cust c WHERE c.cId = :cId")
//    String findNickBycId(@Param("cId") int cId);
//
//
//    @Query("SELECT c.regDt FROM Cust c WHERE c.cId = :cId")
//    Date findRegDtBycId(@Param("cId") int cId);




}
