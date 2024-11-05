package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public int remove(Integer bno, Integer cId) {
        Optional<Board> board = boardRepository.findById(bno);
        if (board.isPresent() && board.get().getCId().equals(cId)) {
            boardRepository.deleteById(bno);
            return 1;
        }
        return 0;
    }

    @Override
    public int write(Board board) {
        boardRepository.save(board);
        return 1;
    }

    @Override
    public List<Board> getList() {
        return boardRepository.findAll();
    }

    @Transactional
    @Override
    public Board read(Integer bno) {
        Optional<Board> board = boardRepository.findById(bno);
        board.ifPresent(b -> b.setViewCnt(b.getViewCnt() + 1));
        return board.orElse(null);
    }

    @Override
    public List<Board> getPage(int page, int size) {
        // 페이지 처리를 Pageable 사용이 필요합니다.
        return boardRepository.findAll(PageRequest.of(page, size)).getContent();
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
//    public Page<BoardDto> getSearchResultPage(String keyword, int deleted, Pageable pageable) {
//        Page<Board> boardPage = boardRepository.findByTitleContainingOrContentContaining(keyword, deleted, pageable);
//
//        // Board -> BoardDto 변환
////        이거 엔티티였음
//        return boardPage.map(board -> new BoardDto(board.getCId(), board.getTitle(), board.getContent(), board.getWriter(), board.getViewCnt()));
//    }


    @Override
    public Page<BoardDto> getSearchResultPage(String title, String content, int deleted, Pageable pageable) {
        Page<Board> boardPage = boardRepository.findByTitleContainingOrContentContaining(
                title, content, deleted, pageable);
        return boardPage.map(board -> new BoardDto(board.getCId(), board.getTitle(), board.getContent(),
                board.getWriter(), board.getViewCnt()));
    }


    @Override
    public int getSearchResultCnt(String keyword) {
        return boardRepository.countByTitleContainingOrContentContaining(keyword, keyword);
    }

}
