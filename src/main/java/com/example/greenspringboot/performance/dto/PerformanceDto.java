package com.example.greenspringboot.performance.dto;


import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class PerformanceDto {
    private Integer pno;
    private String title;
    private String artist;
    private String genre;
    private String venue;
    private String duration;
    private String rating;
    private String date;
    private String content;
    private MultipartFile imgFile;
    private String img;
    private Integer price;
    private Boolean deleted;
    private Date createdAt;
    private Integer createdBy;
    private Date updatedAt;
    private Integer updatedBy;
}
