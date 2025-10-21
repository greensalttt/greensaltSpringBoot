package com.example.greenspringboot.performance.controller;
import com.example.greenspringboot.board.paging.PageHandler;
import com.example.greenspringboot.board.paging.PageHandler12;
import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.paging.SearchCondition12;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Controller
@RequestMapping("/performance")
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/list")
    public String PerformanceList(Model m, SearchCondition12 sc) {
        try {
            int totalCnt = performanceService.getSearchResultCnt(sc);
            PageHandler12 pageHandler12 = new PageHandler12(totalCnt, sc);
            List<PerformanceDto> list = performanceService.getSearchResultPage(sc);

            m.addAttribute("totalCnt", totalCnt);
            m.addAttribute("list", list);
            m.addAttribute("ph", pageHandler12);


            Instant startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
            m.addAttribute("startOfToday", startOfToday.toEpochMilli());
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("msg", "LIST_ERR");
            m.addAttribute("totalCnt", 0);
        }

        return "performanceList";
    }



    @GetMapping("/read")
    public String PerformanceRead(Integer pno, Model m){
        performanceService.performanceRead(pno, m);
        return "performance";
    }


}
