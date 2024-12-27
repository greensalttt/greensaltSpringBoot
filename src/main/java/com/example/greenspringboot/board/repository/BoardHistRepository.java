package com.example.greenspringboot.board.repository;

import com.example.greenspringboot.board.entity.BoardHist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardHistRepository  extends JpaRepository<BoardHist, Integer> {
}
