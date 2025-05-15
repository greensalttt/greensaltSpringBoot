package com.example.greenspringboot.album.repository;

import com.example.greenspringboot.album.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

    // ano 기준 내림차순 정렬
    List<Album> findAllByOrderByAnoDesc();
}
