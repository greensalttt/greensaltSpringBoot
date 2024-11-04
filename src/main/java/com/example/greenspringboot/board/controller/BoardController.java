package com.example.greenspringboot.board.controller;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Controller
//오토와이드 대신에 사용 가능
//@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

//    private final BoardService boardService;

    @Autowired
    private BoardService boardService;

    @GetMapping("/list")
    public String list(Model m,
                       @RequestParam(value = "keyword", defaultValue = "") String keyword,
                       @RequestParam(value = "page", defaultValue = "1") int page,
                       @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                       HttpSession session) {
        try {
            // PageRequest를 이용하여 0-based 인덱스를 설정합니다.
            PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

            // 검색 키워드를 적용하여 페이징된 결과를 가져옵니다.
            Page<BoardDto> boardPage = boardService.getSearchResultPage(keyword, pageRequest);
            m.addAttribute("list", boardPage.getContent());
            m.addAttribute("totalCnt", boardPage.getTotalElements());
            m.addAttribute("currentPage", boardPage.getNumber() + 1);  // 1-based page number
            m.addAttribute("totalPages", boardPage.getTotalPages());
            m.addAttribute("pageSize", pageSize);

            // 오늘 시작 시간을 밀리초로 추가
            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        System.out.println("게시판 겟맵핑: " + session.getAttribute("cName"));
        return "boardList"; // 로그인 상태이면 게시판 화면으로 이동
    }
}
