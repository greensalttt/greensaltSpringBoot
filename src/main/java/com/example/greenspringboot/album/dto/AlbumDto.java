package com.example.greenspringboot.album.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
public class AlbumDto {
    private Integer ano;
    private String domestic;
    private String type;
    private String genre;
    private String title;
    private String artist;
    private String content;
    private MultipartFile imgFile;
    private String img;
    private String released;
    private Boolean deleted;
    private Date regDt;
    private Date upDt;
}

