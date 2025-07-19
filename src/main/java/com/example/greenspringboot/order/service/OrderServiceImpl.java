package com.example.greenspringboot.order.service;

import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void orderPage(Integer pno, Model m){
        Performance performance =  performanceRepository.findById(pno)
                .orElseThrow(()-> new IllegalArgumentException("공연 데이터 없음"));

        PerformanceDto performanceDto = PerformanceDto.builder()
                .pno(performance.getPno())
                .title(performance.getTitle())
                .price(performance.getPrice())
                .build();

        m.addAttribute("performanceDto", performanceDto);
    }


//    @Override
//    public PerformanceDto orderPage(Integer pno) {
//        Performance performance = performanceRepository.findById(pno)
//                .orElseThrow(() -> new IllegalArgumentException("공연 데이터 없음"));
//
//        return PerformanceDto.builder()
//                .pno(performance.getPno())
//                .title(performance.getTitle())
//                .price(performance.getPrice())
//                .build();
//
//    }

}
