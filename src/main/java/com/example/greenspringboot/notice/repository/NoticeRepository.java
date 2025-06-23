package com.example.greenspringboot.notice.repository;

import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    List<Notice> findAllByDeletedFalseOrderByNnoDesc();


    @Modifying
    @Query("UPDATE Notice SET viewCnt = viewCnt + 1 WHERE nno = :nno")
    int incrementViewCnt(@Param("nno") Integer nno);


    Notice findByNno(Integer nno);
}
