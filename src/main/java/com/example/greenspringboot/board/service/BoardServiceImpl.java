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

//    @Override
//    public List<Board> getList() {
//        return boardRepository.findAll();
//    }
//
//    @Override
//    public List<Board> getPage(int page, int size) {
//        // 페이지 처리를 Pageable 사용이 필요합니다.
//        return boardRepository.findAll(PageRequest.of(page, size)).getContent();
//    }

    @Override
    public Page<BoardDto> getSearchResultPage(SearchCondition sc, Pageable pageable) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        // option에 따라 title 또는 content 검색
        Page<Board> boardPage;
        if ("title".equals(option)) {
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, "", pageable);
        } else if ("content".equals(option)) {
            boardPage = boardRepository.findByTitleContainingOrContentContaining("", keyword, pageable);
        } else {
            // title과 content 모두 검색하는 경우
            boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, pageable);
        }

        // Pageable에 Sort 추가 (bno 내림차순)
        Pageable descPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Order.desc("bno")));

        // Board 엔티티를 BoardDto로 변환하여 반환 (빌더 패턴 사용)
        Page<Board> descBoardPage = boardRepository.findAll(descPageable);

        // Board 엔티티를 BoardDto로 변환하여 반환 (빌더 패턴 사용)
        // 화면에 보이기
        return descBoardPage.map(board ->
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
