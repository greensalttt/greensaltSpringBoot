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
                .name(custDto.getName())
                .phn(custDto.getPhn())
                .build();
        custRepository.save(cust);

        return "success";
    }
}
