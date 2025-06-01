package com.example.greenspringboot.performance.service;

import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.paging.SearchCondition12;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface PerformanceService {

    boolean write(PerformanceDto performanceDto, HttpSession session);

    void performanceRead(Integer pno, Model m);

    List<PerformanceDto> getSearchResultPage(SearchCondition12 sc);

    int getSearchResultCnt(SearchCondition12 sc);

    List<PerformanceDto> toDtoList(List<Performance> performanceList);

    void performanceList(Model m);
}
