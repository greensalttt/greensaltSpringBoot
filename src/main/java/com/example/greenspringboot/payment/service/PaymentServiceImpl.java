package com.example.greenspringboot.payment.service;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PerformanceRepository performanceRepository;

//    @Override
//    public void paymentPage(Integer pno, Model m){
//        Performance performance =  performanceRepository.findById(pno)
//                .orElseThrow(()-> new IllegalArgumentException("공연 데이터 없음"));
//
//        Integer ppno = performance.getPno();
//        String title = performance.getTitle();
//        Integer price = performance.getPrice();
//
//        PerformanceDto performanceDto = PerformanceDto.builder()
//                .pno(ppno)
//                .title(title)
//                .price(price)
//                .build();
//
//        m.addAttribute("performanceDto", performanceDto);
//    }
}
