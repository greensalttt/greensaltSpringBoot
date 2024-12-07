package com.example.greenspringboot.board.controller;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.paging.PageHandler;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    @GetMapping("/list")
//    public String list(Model m, SearchCondition sc, Pageable pageable) {
//
//        try {
//            int totalCnt = boardService.getCount();
//            m.addAttribute("totalCnt", totalCnt);
//            System.out.println("게시글 총갯수: " + totalCnt);
//
//            Page<BoardDto> page = boardService.getSearchResultPage(sc, pageable);
//            m.addAttribute("page", page);
//
//            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
//            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
//        } catch (Exception e) {
//            e.printStackTrace();
//            m.addAttribute("msg", "LIST_ERR");
//            m.addAttribute("totalCnt", 0);
//        }
//        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
//    }

    @GetMapping("/list")
    public String list(Model m, SearchCondition sc) {
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            m.addAttribute("totalCnt", totalCnt);

            PageHandler pageHandler = new PageHandler(totalCnt, sc);
            List<BoardDto> list = boardService.getSearchResultPage(sc);

            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    @GetMapping("/write")
    public String write(Model m){
        m.addAttribute("mode","new");
        return "board";
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {

        boardDto.setCId((Integer) session.getAttribute("cId"));
        boardDto.setWriter((String) session.getAttribute("cNick"));

        try {
            int rowCnt = boardService.write(boardDto);
            if(rowCnt !=1)
                throw new Exception("Write failed");
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute(boardDto);
            m.addAttribute("msg", "WRT_ERR");

            return "board";
        }
    }
}
