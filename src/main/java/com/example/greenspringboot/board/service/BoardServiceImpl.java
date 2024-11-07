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
    public void write(BoardDto boardDto) {
        Board board = Board.builder()
                .cId(boardDto.getCId())
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter())
                .deleted(boardDto.getDeleted())
                .build();

        // Board 엔티티 저장
        boardRepository.save(board);
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

    public Page<BoardDto> getSearchResultPage(String title, String content, Pageable pageable) {
        // JPA에서 Pageable을 사용하여 페이징 처리된 결과를 조회
        Page<Board> boardPage = boardRepository.findByTitleContainingOrContentContaining(title, content, pageable);

        // Board 엔티티를 BoardDto로 변환하여 반환
        return boardPage.map(board -> new BoardDto(board.getCId(), board.getTitle(), board.getContent(),
                board.getWriter(), board.getViewCnt()));
    }


    @Override
    public int getSearchResultCnt(String keyword) {
        return boardRepository.countByTitleContainingOrContentContaining(keyword, keyword);
    }

}
