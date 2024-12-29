package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;

import java.util.List;

public interface BoardService {


    void write(BoardDto boardDto);

    BoardDto read(Integer bno);

    void boardModify(BoardDto boardDto, Integer cId, Integer bno, BoardDto oldData);

    List<BoardDto> getSearchResultPage(SearchCondition sc);

    void toEntity(Board board, BoardDto boardDto);

    List<BoardDto> toDto(List<Board> boardList);

    int getSearchResultCnt(SearchCondition sc);

    BoardDto toDto(Board board);

    void delete(Integer cId, Integer bno);

}
