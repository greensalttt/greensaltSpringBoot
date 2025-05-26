package com.example.greenspringboot.board.repository;
import com.example.greenspringboot.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 제목 또는 내용을 키워드로 검색하는 메서드

    List<Board> findByTitleContainingAndDeletedFalse(String title, Sort sort);

    List<Board> findByContentContainingAndDeletedFalse(String content, Sort sort);

    // 작성자 이름으로 게시물을 찾는 메서드
    List<Board> findByWriterContainingAndDeletedFalse(String writer, Sort sort);


//    연산이 복잡할 경우 직접 쿼리를 생성해서 해야 오류가 안난다. SQL에서 or보다 and가 우선순위가 더 높기때문에 이 JPA는 의도와 다르게 작동했음
    @Query("SELECT b FROM Board b WHERE (b.title LIKE %:keyword% OR b.content LIKE %:keyword%) AND b.deleted = false")
    List<Board> findByTitleContainingOrContentContainingAndDeletedFalse(@Param("keyword") String keyword, Sort sort);


    // 검색 조건에 맞는 게시물의 개수를 세는 메서드
    @Query("SELECT COUNT(b) FROM Board b WHERE (b.title LIKE %:title% OR b.content LIKE %:content%) AND b.deleted = false")
    int countByTitleContainingOrContentContainingAndDeletedFalse(@Param("title") String title, @Param("content") String content);


    int countByTitleContainingAndDeletedFalse(String title);

    int countByContentContainingAndDeletedFalse(String content);

    int countByWriterContainingAndDeletedFalse(String writer);

    Board save(Board board);

    Board findByBno(Integer bno);

    Board findBycIdAndBno(Integer cId, Integer bno);

    // 게시글 조회 시 조회수(view_cnt) 1 증가
    @Modifying
    @Query("UPDATE Board SET viewCnt = viewCnt + 1 WHERE bno = :bno")
    int incrementViewCnt(@Param("bno") Integer bno);


    Long countBycIdAndDeletedFalse(Integer cId);
}

////      JPA 방식으로 사용할때 순서
//// DB 테이블 > 엔티티 만들기 > DTO 만들기 > 레포 만들기 > 나머지 로직/