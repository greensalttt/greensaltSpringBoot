package com.example.greenspringboot.board.repository;

import com.example.greenspringboot.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 제목 또는 내용을 키워드로 검색하는 메서드
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    // 검색 조건에 맞는 게시물의 개수를 세는 메서드
    int countByTitleContainingOrContentContaining(String title, String content);
}

//
//
////      JPA 방식으로 사용할때 순서
//
////1. DB 테이블 > 엔티티 만들기 > DTO 만들기 > 레포 만들기 > 나머지 로직/