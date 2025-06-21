package com.example.greenspringboot.notice.repository;

import com.example.greenspringboot.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
}
