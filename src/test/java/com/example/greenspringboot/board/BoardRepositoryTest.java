package com.example.greenspringboot.board;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void insertTest() throws Exception {
                BoardDto boardDto = BoardDto.builder()
                .cId(1)
                .title("테스트")
                .content("ㅇㅇ")
                .writer("test")
                .deleted(1)
                .build();
        boardService.write(boardDto);
    }
}
