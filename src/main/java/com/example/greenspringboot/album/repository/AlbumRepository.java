package com.example.greenspringboot.album.repository;

import com.example.greenspringboot.album.entity.Album;
import com.example.greenspringboot.board.entity.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // ano 기준 내림차순 정렬
    List<Album> findAllByOrderByAnoDesc();

    Optional<Album> findByAno(Integer ano);

    List<Album> findByTitleContainingAndDeletedFalse(String keyword, Sort sort);

    List<Album> findByArtistContainingAndDeletedFalse(String keyword, Sort sort);

    int countByTitleContainingAndDeletedFalse(String keyword);

    int countByArtistContainingAndDeletedFalse(String keyword);

    //    연산이 복잡할 경우 직접 쿼리를 생성해서 해야 오류가 안난다. SQL에서 or보다 and가 우선순위가 더 높기때문에 이 JPA는 의도와 다르게 작동했음
    @Query("SELECT a FROM Album a WHERE (a.title LIKE %:keyword% OR a.artist LIKE %:keyword%) AND a.deleted = false")
    List<Album> findByTitleContainingOrArtistContainingAndDeletedFalse(@Param("keyword") String keyword, Sort sort);


    // 검색 조건에 맞는 게시물의 개수를 세는 메서드
    @Query("SELECT COUNT(a) FROM Album a WHERE (a.title LIKE %:title% OR a.artist LIKE %:artist%) AND a.deleted = false")
    int countByTitleContainingOrArtistContainingAndDeletedFalse(@Param("title") String title, @Param("artist") String artist);

}
