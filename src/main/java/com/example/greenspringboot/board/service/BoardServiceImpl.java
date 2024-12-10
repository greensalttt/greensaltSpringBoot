package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public int getCount() {
        return (int) boardRepository.count();
    }

    @Override
    public int write(BoardDto boardDto) {
        Board board = Board.builder()
                .bno(boardDto.getBno())
                .cId(boardDto.getCId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
//                .deleted(boardDto.getDeleted())
                .build();
        // Board 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        boardRepository.save(board);
        return 1;
    }

    @Transactional
    @Override
    public Board read(Integer bno) {
        Optional<Board> board = boardRepository.findById(bno);
        board.ifPresent(b -> b.setViewCnt(b.getViewCnt() + 1));
        return board.orElse(null);
    }

    @Override
    public int remove(Integer bno, Integer cId) {
        Optional<Board> board = boardRepository.findById(bno);
        if (board.isPresent() && board.get().getCId().equals(cId)) {
            boardRepository.deleteById(bno);
            return 1;
        }
        return 0;
    }

    @Override
    public int modify(Board board) {
        if (boardRepository.existsById(board.getBno())) {
            boardRepository.save(board);
            return 1;
        }
        return 0;
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

//    @Override
//    public int getSearchResultCnt(SearchCondition sc) {
//        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
//        String keyword = sc.getKeyword();
//        String option = sc.getOption();
//
//        // option에 따라 title 또는 content 검색
//        if ("title".equals(option)) {
//            return boardRepository.countByTitleContainingOrContentContainingOrWriterContaining(keyword, "", "");
//        } else if ("content".equals(option)) {
//            return boardRepository.countByTitleContainingOrContentContainingOrWriterContaining("", keyword, "");
//        }  else if ("writer".equals(option)) {
//            return boardRepository.countByTitleContainingOrContentContainingOrWriterContaining("","", keyword);
//        }
//        else {
//            // title과 content 모두 검색하는 경우
//            return boardRepository.countByTitleContainingOrContentContainingOrWriterContaining(keyword, keyword, keyword);
//        }
//    }

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
        // Convert the list of Board entities to BoardDto list
        return boardList.stream()
                .map(board -> BoardDto.builder()
                        .bno(board.getBno())
                        .cId(board.getCId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .regDt(board.getRegDt())
                        .viewCnt(board.getViewCnt())
                        .build())
                .collect(Collectors.toList());
    }
}
