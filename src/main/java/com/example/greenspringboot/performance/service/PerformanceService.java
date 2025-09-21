package com.example.greenspringboot.performance.service;

import com.example.greenspringboot.board.paging.SearchCondition;
import com.example.greenspringboot.board.paging.SearchCondition12;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface PerformanceService {

    void performanceRead(Integer pno, Model m);

    void write(PerformanceDto performanceDto, Integer aId);

    List<PerformanceDto> getSearchResultPage(SearchCondition12 sc);

    int getSearchResultCnt(SearchCondition12 sc);

    List<PerformanceDto> toDtoList(List<Performance> performanceList);

    void performanceList(Model m);

    boolean performanceModify(PerformanceDto performanceDto, MultipartFile imgFile, HttpSession session) throws IOException;

    PerformanceDto toDto(Performance performance);

    void toEntity(Performance performance, PerformanceDto performanceDto, MultipartFile imgFile) throws IOException;

    boolean performanceRemove(Integer pno);

    int getPriceByPno(Integer pno);

    List<PerformanceDto> getAllPerformances();
}
