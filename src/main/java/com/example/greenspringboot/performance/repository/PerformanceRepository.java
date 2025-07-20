package com.example.greenspringboot.performance.repository;
import com.example.greenspringboot.performance.entity.Performance;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PerformanceRepository extends JpaRepository<Performance, Integer> {

List<Performance> findAllByDeletedFalseOrderByPnoDesc();

    Long countByDeletedFalse();

    List<Performance> findByTitleContainingAndDeletedFalse(String keyword, Sort sort);

    List<Performance> findByArtistContainingAndDeletedFalse(String keyword, Sort sort);

    int countByTitleContainingAndDeletedFalse(String keyword);

    int countByArtistContainingAndDeletedFalse(String keyword);

    //    연산이 복잡할 경우 직접 쿼리를 생성해서 해야 오류가 안난다. SQL에서 or보다 and가 우선순위가 더 높기때문에 이 JPA는 의도와 다르게 작동했음
    @Query("SELECT p FROM Performance p WHERE (p.title LIKE %:keyword% OR p.artist LIKE %:keyword%) AND p.deleted = false")
    List<Performance> findByTitleContainingOrArtistContainingAndDeletedFalse(@Param("keyword") String keyword, Sort sort);


    // 검색 조건에 맞는 게시물의 개수를 세는 메서드
    @Query("SELECT COUNT(p) FROM Performance p WHERE (p.title LIKE %:title% OR p.artist LIKE %:artist%) AND p.deleted = false")
    int countByTitleContainingOrArtistContainingAndDeletedFalse(@Param("title") String title, @Param("artist") String artist);


}
