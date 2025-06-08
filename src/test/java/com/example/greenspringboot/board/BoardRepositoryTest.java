package com.example.greenspringboot.board;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.repository.BoardRepository;
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

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        boardRepository.deleteAll(); // 기존 데이터 삭제
        Integer cId = 2;
        for (int i = 1; i < 100; i++){
            BoardDto boardDto = BoardDto.builder()
                    .createdBy(cId)
                    .title("test" + i)
                    .content("ㅇㅇ")
                    .writer("greensalt")
                    .deleted(false)
                    .build();
            boardService.write(boardDto, cId);
        }
    }

}
