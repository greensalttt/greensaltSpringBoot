package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BoardService {
    int getCount();

    int remove(Integer bno, Integer cId);

    int write(Board board);

    List<Board> getList();

    @Transactional
    Board read(Integer bno);

    List<Board> getPage(int page, int size);

    int modify(Board board);

    List<Board> getSearchResultPage(String keyword, int page, int size);

    int getSearchResultCnt(String keyword);
}
