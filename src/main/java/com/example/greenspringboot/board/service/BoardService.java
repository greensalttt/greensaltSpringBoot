package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    int getCount();

    int remove(Integer bno, Integer cId);

    int write(BoardDto boardDto);

//    List<Board> getList();

    Board read(Integer bno);

//    List<Board> getPage(int page, int size);

    int modify(Board board);

//    Page<BoardDto> getSearchResultPage(String titleKeyword, String contentKeyword, Pageable pageable);
//    List <BoardDto> getSearchResultPage(SearchCondition sc);

    void getSearchResultPage(SearchCondition sc);


    BoardDto toDto(Board board);


    int getSearchResultCnt(SearchCondition sc);
}
