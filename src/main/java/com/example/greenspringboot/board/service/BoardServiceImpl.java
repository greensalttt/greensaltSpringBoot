package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
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

    @Override
    public List<Board> getSearchResultPage(String keyword, int page, int size) {
        // 페이지를 고려하여 검색 결과를 가져옵니다.
        Page<Board> result = boardRepository.findByTitleContainingOrContentContaining(keyword, keyword, PageRequest.of(page, size));
        return result.getContent();
    }

    @Override
    public int getSearchResultCnt(String keyword) {
        // 검색 조건에 맞는 게시물의 개수를 반환합니다.
        return boardRepository.countByTitleContainingOrContentContaining(keyword, keyword);
    }
}
