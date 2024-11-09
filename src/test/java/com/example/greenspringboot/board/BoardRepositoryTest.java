package com.example.greenspringboot.board;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Test
    public void insertTest() throws Exception {
                BoardDto boardDto = BoardDto.builder()
                .cId(1)
                .title("드디어")
                .content("성공")
                .writer("띄웠다")
                .deleted(1)
                .build();
        boardService.write(boardDto);
    }
}
