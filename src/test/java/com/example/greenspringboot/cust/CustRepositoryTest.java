package com.example.greenspringboot.cust;

import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustRepositoryTest {

    @Autowired private CustRepository custRepository;

    @Test
    public void crudTest() {
        Cust cust = Cust.builder()
                .name("김김김")
                .phn("123456789")
                .build();

        // creat test
        custRepository.save(cust);

        Cust foundCust = custRepository.findById(1).get();
    }
}
