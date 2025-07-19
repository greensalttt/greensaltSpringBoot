package com.example.greenspringboot.order.service;

import com.example.greenspringboot.performance.dto.PerformanceDto;
import org.springframework.ui.Model;

public interface OrderService {
    void orderPage(Integer pno, Model m);

//    PerformanceDto orderPage(Integer pno);
}
