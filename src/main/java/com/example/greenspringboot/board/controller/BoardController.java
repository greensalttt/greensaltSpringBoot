package com.example.greenspringboot.board.controller;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.paging.PageHandler;
import com.example.greenspringboot.board.paging.PageHandler15;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.paging.SearchCondition15;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/list")
    public String list(Model m, SearchCondition15 sc) {
        try {
            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler15 pageHandler15 = new PageHandler15(totalCnt, sc);
            List<BoardDto> list = boardService.getSearchResultPage(sc);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler15);

            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "boardList";
    }

    @GetMapping("/write")
    public String write() {
        return "boardWrite";
    }


    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, RedirectAttributes rattr, HttpSession session) {

        Integer cId = (Integer) session.getAttribute("cId");
        boardDto.setCreatedBy((Integer) session.getAttribute("cId"));

        try {
            boardService.write(boardDto,cId);
            rattr.addFlashAttribute("msg", "WRT_OK");
            return "redirect:/board/list";
        } catch (Exception e) {
            m.addAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        BoardDto boardDto = boardService.read(bno);

        m.addAttribute(boardDto);
        m.addAttribute("page", page);
        m.addAttribute("pageSize", pageSize);
        return "board";
    }

    @GetMapping("/modify")
    public String modifyForm(Model model, Integer bno) {
        BoardDto boardDto = boardService.read(bno);
        model.addAttribute("boardDto", boardDto);
        model.addAttribute("mode", "modify");
        return "boardWrite";
    }


//    수정시 닉네임 안보이는 문제
    @PostMapping("/modify")
    public String modify(BoardDto boardDto, HttpSession session, Integer bno, Model m){
        Integer createdBy = (Integer) session.getAttribute("cId");

        BoardDto oldData = boardService.read(bno);
        boardDto.setCreatedBy(createdBy);
        boardService.boardModify(boardDto, createdBy, bno, oldData);

        BoardDto updatedBoardDto = boardService.read(bno);
        m.addAttribute("boardDto", updatedBoardDto);

        m.addAttribute("msg", "MOD_OK");
        return "board";
    }

    @PostMapping("/remove")
    public String remove(BoardDto boardDto, HttpSession session, Integer bno, RedirectAttributes rattr){
        Integer createdBy = (Integer) session.getAttribute("cId");
        boardDto.setCreatedBy(createdBy);
        boardService.remove(createdBy, bno, boardDto);
        rattr.addFlashAttribute("msg", "DEL_OK");
        return "redirect:/board/list";
    }
}
