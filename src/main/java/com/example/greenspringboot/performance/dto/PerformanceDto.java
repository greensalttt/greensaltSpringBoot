package com.example.greenspringboot.performance.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class PerformanceDto {
    private Integer pno;
    private Integer aId;
    private String title;
    private String artist;
    private String region;
    private String genre;
    private String venue;
    private String duration;
    private String rating;
    private String date;
    private String content;
    private MultipartFile imgFile;
    private String img;
    private Boolean deleted;
    private Date regDt;
    private Date upDt;
}
