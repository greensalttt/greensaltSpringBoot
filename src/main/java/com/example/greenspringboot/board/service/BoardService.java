package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;

import java.util.List;

public interface BoardService {


    void write(BoardDto boardDto);

    BoardDto read(Integer bno);

//    void read(Integer bno);

    List<BoardDto> getSearchResultPage(SearchCondition sc);


     List<BoardDto> toDto(List<Board> boardList);

     BoardDto toDto2 (Board board);

    int getSearchResultCnt(SearchCondition sc);

}
