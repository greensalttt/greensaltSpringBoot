package com.example.greenspringboot.board.service;

import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.entity.BoardHist;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.repository.BoardHistRepository;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardHistRepository boardHistRepository;

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
    @Transactional
    public BoardDto read(Integer bno) {
        boardRepository.incrementViewCnt(bno); // 조회수 1 증가
        Board board = boardRepository.findByBno(bno);
        return toDto(board);
    }

    //삭제

    @Override
    public void remove(Integer cId, Integer bno){
        Board board = boardRepository.findBycIdAndBno(cId, bno);
        board.setDeleted(true);
        boardRepository.save(board);
    }


    @Transactional
    @Override
    public void boardModify(BoardDto boardDto, Integer cId, Integer bno, BoardDto oldData) {

        Board board = boardRepository.findBycIdAndBno(cId, bno);

        // 기존 dto를 엔티티로 변환
        toEntity(board, boardDto);
        // 바뀐 개인정보 저장
        boardRepository.save(board);

        List<BoardHistDto> boardHistDtoList = new ArrayList<>();

        addBoardHist(boardHistDtoList, boardDto, "TITLE", oldData.getTitle(), boardDto.getTitle());
        addBoardHist(boardHistDtoList, boardDto, "CONTENT", oldData.getContent(), boardDto.getContent());

        for (BoardHistDto boardHistDto : boardHistDtoList) {

            BoardHist boardHist = new BoardHist();
            boardHist.setBno(boardHistDto.getBno());
            boardHist.setCId(boardHistDto.getCId());
            boardHist.setBCngCd(boardHistDto.getBCngCd());
            boardHist.setBBf(boardHistDto.getBBf());
            boardHist.setBAf(boardHistDto.getBAf());

            // 이력 저장
            boardHistRepository.save(boardHist);
        }
    }

    private void addBoardHist(List<BoardHistDto> boardHistDtoList, BoardDto newData,
                              String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            BoardHistDto boardHistDto = new BoardHistDto();
            boardHistDto.setBno(newData.getBno());
            boardHistDto.setCId(newData.getCId());
            boardHistDto.setBCngCd(changeCode);
            boardHistDto.setBBf(oldValue);
            boardHistDto.setBAf(newValue);
            boardHistDtoList.add(boardHistDto);
        }}

    @Override
    public void toEntity(Board board, BoardDto boardDto) {
        if (boardDto.getBno() != null) {
            board.setBno(boardDto.getBno());
        }
        if (boardDto.getCId() != null) {
            board.setCId(boardDto.getCId());
        }
        if (boardDto.getTitle() != null) {
            board.setTitle(boardDto.getTitle());
        }
        if (boardDto.getContent() != null) {
            board.setContent(boardDto.getContent());
        }
        if (boardDto.getWriter() != null) {
            board.setWriter(boardDto.getWriter());
        }
        if (boardDto.getViewCnt() != null) {
            board.setViewCnt(boardDto.getViewCnt());
        }
        if (boardDto.getCommentCnt() != null) {
            board.setCommentCnt(boardDto.getCommentCnt());
        }
        if (boardDto.getDeleted() != null) {
            board.setDeleted(boardDto.getDeleted());
        }
        if (boardDto.getRegDt() != null) {
            board.setRegDt(boardDto.getRegDt());
        }
        if (boardDto.getUpDt() != null) {
            board.setUpDt(boardDto.getUpDt());
        }
    }


    @Override
    public List<BoardDto> getSearchResultPage(SearchCondition sc) {
        // 게시글 조회 메서드, 게시글 목록 조회니까 그 게시글의 작성자도 같이 가져와야되는게 아닐까?
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        Sort sort = Sort.by(Sort.Order.desc("bno")); // 등록일 기준으로 내림차순 정렬
        List<Board> boardList;

        if ("title".equals(option)) {
            boardList = boardRepository.findByTitleContainingAndDeletedFalse(keyword, sort);  // 제목만 검색
        } else if ("content".equals(option)) {
            boardList = boardRepository.findByContentContainingAndDeletedFalse(keyword, sort);  // 내용만 검색
        } else if ("writer".equals(option)) {
            boardList = boardRepository.findByWriterContainingAndDeletedFalse(keyword, sort);  // 작성자만 검색
        } else {
            boardList = boardRepository.findByTitleContainingOrContentContainingAndDeletedFalse(keyword, sort);  // 제목 + 내용 검색
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
            return boardRepository.countByTitleContainingAndDeletedFalse(keyword);
        } else if ("content".equals(option)) {
            return boardRepository.countByContentContainingAndDeletedFalse(keyword);
        }  else if ("writer".equals(option)) {
            return boardRepository.countByWriterContainingAndDeletedFalse(keyword);
        }
        else {
            // title과 content 모두 검색하는 경우
            return boardRepository.countByTitleContainingOrContentContainingAndDeletedFalse(keyword, keyword);
        }
    }


    // 여러개 게시글 한번에
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


    // 단일 게시글
    @Override
    public BoardDto toDto(Board board) {
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
