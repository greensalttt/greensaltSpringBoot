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

    Board read(Integer bno);


    int modify(Board board);


    List<BoardDto> getSearchResultPage(SearchCondition sc);


     List<BoardDto> toDto(List<Board> boardList);



    int getSearchResultCnt(SearchCondition sc);
}
