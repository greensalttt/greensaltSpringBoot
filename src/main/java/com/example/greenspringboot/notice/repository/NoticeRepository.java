package com.example.greenspringboot.notice.repository;

import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {

    List<Notice> findAllByDeletedFalseOrderByNnoDesc();

}
