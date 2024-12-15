package com.example.greenspringboot.board.controller;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.PageHandler;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model m, SearchCondition sc, HttpSession session) {
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);
            List<BoardDto> list = boardService.getSearchResultPage(sc);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        System.out.println("게시판 겟맵핑: " + session.getAttribute("cId"));


        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    @GetMapping("/write")
    public String write(Model m, HttpSession session) {
        m.addAttribute("mode", "new");
        System.out.println("글쓰기 겟맵핑: " + session.getAttribute("cId"));
        System.out.println("글쓰기 DTO겟맵핑: " + session.getAttribute("boardDto.cId"));


        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, RedirectAttributes rattr, HttpSession session) {
        boardDto.setCId((Integer) session.getAttribute("cId"));
        boardDto.setWriter((String) session.getAttribute("cNick"));

        try {
            boardService.write(boardDto);
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "WRT_ERR");

            return "board";
        }
    }


//    @GetMapping("/read")
//    public String read(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session) {
//        BoardDto boardDto = boardService.read(bno);
//
//        // 세션에서 cId를 가져와 BoardDto에 설정
//        Integer cId = (Integer) session.getAttribute("cId");
//        boardDto.setCId(cId);
//
//        m.addAttribute("boardDto", boardDto);
//        m.addAttribute("page", page);
//        m.addAttribute("pageSize", pageSize);
//        return "board";
//    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session) {
        BoardDto boardDto = boardService.read(bno);

        // 세션에서 cId를 가져와 BoardDto에 설정
        Integer cId = (Integer) session.getAttribute("cId");

        // cId가 null일 경우 디버깅
        if (cId == null) {
            System.out.println("세션에서 cId를 찾을 수 없습니다.");
        }

        boardDto.setCId(cId);
        System.out.println("boardDto.cId: " + boardDto.getCId()); // 값 확인


        m.addAttribute("boardDto", boardDto);
        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);
        return "board";
    }
}
