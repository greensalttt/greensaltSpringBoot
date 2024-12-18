package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.cust.dto.CustDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public void write(BoardDto boardDto) {
        Board board = Board.builder()
                .bno(boardDto.getBno())
                .cId(boardDto.getCId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .build();
        // Board 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        boardRepository.save(board);
    }

    @Override
    public List<BoardDto> getSearchResultPage(SearchCondition sc) {
        // 검색 조건에 맞는 게시글 조회
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        Sort sort = Sort.by(Sort.Order.desc("bno")); // 등록일 기준으로 내림차순 정렬
        List<Board> boardList;

        if ("title".equals(option)) {
            boardList = boardRepository.findByTitleContaining(keyword, sort);  // 제목만 검색
        } else if ("content".equals(option)) {
            boardList = boardRepository.findByContentContaining(keyword, sort);  // 내용만 검색
        } else if ("writer".equals(option)) {
            boardList = boardRepository.findByWriterContaining(keyword, sort);  // 작성자만 검색
        } else {
            boardList = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, sort);  // 제목 + 내용 검색
        }

        // 검색된 게시글 리스트를 페이지에 맞게 잘라서 반환
        int startIdx = (sc.getPage() - 1) * sc.getPageSize();
        int endIdx = Math.min(startIdx + sc.getPageSize(), boardList.size());

        List<Board> pagedBoardList = boardList.subList(startIdx, endIdx);

        // DTO로 변환해서 반환
        return toDto(pagedBoardList);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        // option에 따라 title 또는 content 검색
        if ("title".equals(option)) {
            return boardRepository.countByTitleContaining(keyword);
        } else if ("content".equals(option)) {
            return boardRepository.countByContentContaining(keyword);
        }  else if ("writer".equals(option)) {
            return boardRepository.countByWriterContaining(keyword);
        }
        else {
            // title과 content 모두 검색하는 경우
            return boardRepository.countByTitleContainingOrContentContaining(keyword, keyword);
        }
    }

    @Override
    public List<BoardDto> toDto(List<Board> boardList) {
        return boardList.stream()
                .map(board -> BoardDto.builder()
                        .bno(board.getBno())
                        .cId(board.getCId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .viewCnt(board.getViewCnt())
                        .commentCnt(board.getCommentCnt())
                        .deleted(board.getDeleted())
                        .regDt(board.getRegDt())
                        .upDt(board.getUpDt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto read(Integer bno) {
        Board board = boardRepository.findByBno(bno);

        // 직접 DTO 변환
        return BoardDto.builder()
                .bno(board.getBno())
                .cId(board.getCId())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .viewCnt(board.getViewCnt())
                .commentCnt(board.getCommentCnt())
                .deleted(board.getDeleted())
                .regDt(board.getRegDt())
                .upDt(board.getUpDt())
                .build();
    }


}
