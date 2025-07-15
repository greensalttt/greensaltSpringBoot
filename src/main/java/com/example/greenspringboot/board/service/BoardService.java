package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.paging.SearchCondition15;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;

public interface BoardService {


    void write(BoardDto boardDto, int cId);

    BoardDto read(Integer bno);

    //    레포에 쿼리를 수정하는 메서드가 있을시 트렌젝션 필수

     void remove(Integer createdBy, Integer bno, BoardDto boardDto);

    void boardModify(BoardDto boardDto, Integer cId, Integer bno, BoardDto oldData);

    boolean adminRemove(BoardDto boardDto, Integer bno);

    void toEntity(Board board, BoardDto boardDto);

    List<BoardDto> toDto(List<Board> boardList);

    List<BoardDto> getSearchResultPage(SearchCondition15 sc);

    int getSearchResultCnt(SearchCondition15 sc);

    BoardDto toDto(Board board);

//    void remove(Integer cId, Integer bno);



}
