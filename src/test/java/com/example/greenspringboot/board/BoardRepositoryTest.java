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

//        BoardDto boardDto = new BoardDto(12341234, "title", "no content",  "닉네임", 0);

                BoardDto boardDto = BoardDto.builder()
                .cId(1)
                .title("게시판")
                .content("성공")
                .writer("합니다")
                .deleted(1)
                .build();
        boardService.write(boardDto);
    }
}


//        Board board = Board.builder()
//                .cId(1)
//                .title("안녕")
//                .content("하세요")
//                .writer("쿠쿠")
//                .deleted(1)
//                .build();