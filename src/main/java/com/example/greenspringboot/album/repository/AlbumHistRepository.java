package com.example.greenspringboot.album.repository;

import com.example.greenspringboot.album.entity.AlbumHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumHistRepository extends JpaRepository<AlbumHist, Integer> {

}
