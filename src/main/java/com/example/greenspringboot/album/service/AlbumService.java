package com.example.greenspringboot.album.service;

import com.example.greenspringboot.album.dto.AlbumDto;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AlbumService {

    void albumList(Model m);

    void albumRead(Integer ano, Model m);

    boolean write(AlbumDto albumDto, MultipartFile imgFile);

    boolean albumRemove(Integer ano);


    }
