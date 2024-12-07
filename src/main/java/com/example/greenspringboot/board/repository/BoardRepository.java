package com.example.greenspringboot.board.repository;
import com.example.greenspringboot.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 게시글 삭제
    void deleteById(Integer id);

    // 제목 또는 내용을 키워드로 검색하는 메서드
//    List<Board> findByTitleContainingOrContentContaining(String title, String content);

    List<Board> findByTitleContainingOrContentContaining(String title, String content, Sort sort);

    // 검색 조건에 맞는 게시물의 개수를 세는 메서드
    int countByTitleContainingOrContentContaining(String title, String content);
}

////      JPA 방식으로 사용할때 순서
//// DB 테이블 > 엔티티 만들기 > DTO 만들기 > 레포 만들기 > 나머지 로직/