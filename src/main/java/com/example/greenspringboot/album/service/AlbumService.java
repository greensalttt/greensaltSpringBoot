package com.example.greenspringboot.album.service;

import org.springframework.ui.Model;

public interface AlbumService {

    void albumList(Model m);

    void albumRead(Integer ano, Model m);


}
