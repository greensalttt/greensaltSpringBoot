package com.example.greenspringboot.cust.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Cust {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer c_id;

    private String name;

    private String phn;
}
