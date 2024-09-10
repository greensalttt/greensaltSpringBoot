package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.dto.CustDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustServiceImpl implements CustService{

    private final CustRepository custRepository;

    @Override
    public String join(CustDto custDto) {
        Cust cust = Cust.builder()
                .c_email(custDto.getC_email())
                .c_pwd(custDto.getC_pwd())
                .c_name(custDto.getC_name())
                .c_nm(custDto.getC_nm())
                .c_birth(custDto.getC_birth())
                .c_gnd(custDto.getC_gnd())// String을 char로 변환
                .c_phn(custDto.getC_phn())
                .c_zip(custDto.getC_zip())
                .c_road_a(custDto.getC_road_a())
                .c_jibun_a(custDto.getC_jibun_a())
                .c_det_a(custDto.getC_det_a())
                .build();
        custRepository.save(cust);

        return "success";
    }
}