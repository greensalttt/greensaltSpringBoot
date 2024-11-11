package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public int getCount() {
        return (int) boardRepository.count();
    }

    @Override
    public void write(BoardDto boardDto) {
        Board board = Board.builder()
                .bno(boardDto.getBno())
                .cId(boardDto.getCId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
//                .regDt(boardDto.getRegDt())
                .deleted(boardDto.getDeleted())
                .build();
        // Board 엔티티 저장, 레포 메서드의 매개변수는 항상 엔티티만 가능
        boardRepository.save(board);
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
    public Page<BoardDto> getSearchResultPage(SearchCondition sc, Pageable pageable) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        // 내림차순 정렬을 위한 변수 설정
        Sort.Order sortOrder = Sort.Order.desc("bno");

        // 페이지 요청에 내림차순 정렬을 추가한 Pageable 객체 생성
        Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortOrder));

        // option에 따라 title 또는 content 검색
        Page<Board> boardPage;
        if ("title".equals(option)) {
            // Title에서 검색하고 내림차순으로 정렬된 결과를 가져옵니다.
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, "", sortedPageable);
        } else if ("content".equals(option)) {
            // Content에서 검색하고 내림차순으로 정렬된 결과를 가져옵니다.
            boardPage = boardRepository.findByTitleContainingOrContentContaining("", keyword, sortedPageable);
        } else {
            // Title과 content 모두에서 검색하고 내림차순으로 정렬된 결과를 가져옵니다.
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, sortedPageable);
        }

        // Board 엔티티를 BoardDto로 변환하여 반환 (빌더 패턴 사용)
        return boardPage.map(board ->
                BoardDto.builder()
                        .bno(board.getBno())
                        .cId(board.getCId())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .regDt(board.getRegDt())
                        .viewCnt(board.getViewCnt())
                        .build()
        );
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        // option에 따라 title 또는 content 검색
        if ("title".equals(option)) {
            return boardRepository.countByTitleContainingOrContentContaining(keyword, "");
        } else if ("content".equals(option)) {
            return boardRepository.countByTitleContainingOrContentContaining("", keyword);
        } else {
            // title과 content 모두 검색하는 경우
            return boardRepository.countByTitleContainingOrContentContaining(keyword, keyword);
        }
    }

}
