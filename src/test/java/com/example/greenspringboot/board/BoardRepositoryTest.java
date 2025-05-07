package com.example.greenspringboot.board;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

@SpringBootTest
public class BoardRepositoryTest {
    @Autowired
    private BoardService boardService;

    @Test
    public void insertTest() {
        Integer cId = 1;
        for (int i = 1; i < 10; i++){
            BoardDto boardDto = BoardDto.builder()
                    .cId(cId)
                    .title("후발주자" + i)
                    .content("ㅇㅇ")
                    .writer("test")
                    .deleted(false)
                    .build();
            boardService.write(boardDto, cId);
        }
    }

}
